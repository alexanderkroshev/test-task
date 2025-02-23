package com.example.hotel.service.impl;

import com.example.hotel.exception.OrderNotValidException;
import com.example.hotel.model.Order;
import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.repository.OrderRepository;
import com.example.hotel.repository.RoomAvailabilityRepository;
import com.example.hotel.service.EmailSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonBookingService {

    private final RoomAvailabilityRepository roomAvailabilityRepository;
    private final OrderRepository orderRepository;
    private final EmailSender emailSender;

    public Optional<Order> createOrder(OrderRequest orderRequest) {
        validateOrder(orderRequest);
        List<LocalDate> daysToBook = getDaysBetween(orderRequest.getFrom(), orderRequest.getTo());
        if (!roomAvailabilityRepository.findByRoomAndDates(orderRequest.getRoomId(), daysToBook)) {
            log.info("no room for specific data for order: {}", orderRequest);
            return Optional.empty();
        }
        val order = toOrder(orderRequest);
        orderRepository.save(order);
        emailSender.sendEmail(order);
        return Optional.of(order);
    }

    private Order toOrder(OrderRequest orderRequest) {
        return new Order(null,
                orderRequest.getRoomId(),
                orderRequest.getUserId(),
                orderRequest.getFrom(),
                orderRequest.getTo()
        );
    }

    private void validateOrder(OrderRequest newOrder) {
        if (newOrder.getUserId() == null
                || newOrder.getRoomId() == null
                || newOrder.getFrom() == null
                || newOrder.getTo() == null) {
            throw new OrderNotValidException("incorrect order request: Missing required fields");
        }
    }

    private List<LocalDate> getDaysBetween(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) {
            throw new OrderNotValidException("incorrect order request: \"from\" is after \"to\"");
        }
        return from.datesUntil(to.plusDays(1)).toList();
    }
}


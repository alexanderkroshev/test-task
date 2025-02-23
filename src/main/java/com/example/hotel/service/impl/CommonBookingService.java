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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class CommonBookingService {

    private final RoomAvailabilityRepository roomAvailabilityRepository;
    private final OrderRepository orderRepository;
    private final EmailSender emailSender;
    private final ModelMapper mapper;

    public boolean createOrder(OrderRequest orderRequest) {
        validateOrder(orderRequest);
        List<LocalDate> daysToBook = daysBetween(orderRequest.getFrom(), orderRequest.getTo());
        val isAvailable = roomAvailabilityRepository.findByRoomAndDates(orderRequest.getRoomId(), daysToBook);
        if (isAvailable) {
            val order = mapper.map(orderRequest, Order.class);
            orderRepository.save(order);
            emailSender.sendEmail(order);
            return true;
        }
        log.info("no room for specific data for order: {}", orderRequest);
        return false;
    }

    private void validateOrder(OrderRequest newOrder) {
        if (newOrder.getUserId() == null
                || newOrder.getRoomId() == null
                || newOrder.getFrom() == null
                || newOrder.getTo() == null) {
            throw new OrderNotValidException("incorrect order request");
        }
    }

    private List<LocalDate> daysBetween(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) return Collections.emptyList();
        return from.datesUntil(to.plusDays(1)).toList();
    }
}


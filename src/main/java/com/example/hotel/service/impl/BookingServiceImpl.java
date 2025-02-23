package com.example.hotel.service.impl;

import com.example.hotel.exception.OrderNotValidException;
import com.example.hotel.model.Order;
import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.repository.OrderRepository;
import com.example.hotel.repository.RoomAvailabilityRepository;
import com.example.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceImpl implements BookingService {

    private final RoomAvailabilityRepository roomAvailabilityRepository;
    private final OrderRepository orderRepository;
    private final ModelMapper mapper;

    @Override
    public boolean createOrder(OrderRequest newOrder) {
        validateOrder(newOrder);
        List<LocalDate> daysToBook = daysBetween(newOrder.getFrom(), newOrder.getTo());

        val isAvailable = roomAvailabilityRepository.findByRoomAndDates(newOrder.getRoomId(), daysToBook);
        if (isAvailable) {
            orderRepository.save(mapper.map(newOrder, Order.class));
            return true;
        }
        return false;
    }

    private void validateOrder(OrderRequest newOrder) {
        if (newOrder.getRoomId() == null
                || newOrder.getUserId() == null
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

package com.example.hotel.service.impl;


import com.example.hotel.model.RoomAvailability;
import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.repository.OrderRepository;
import com.example.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final RoomAvailability roomAvailability;

    @Override
    public void createOrder(OrderRequest newOrder) {
        List<LocalDate> daysToBook = daysBetween(newOrder.getFrom(), newOrder.getTo());
        Set<LocalDate> unavailableDays = new HashSet<>(daysToBook);
        val availability = orderRepository.findAll();

        for (LocalDate dayToBook : daysToBook) {
            for (RoomAvailability room : availability) {
                if (!room.getDate().equals(dayToBook) || room.getQuota() < 1) {
                    continue;
                }
                room.setQuota(room.getQuota() - 1);
                unavailableDays.remove(dayToBook);
            }
        }

        if (!unavailableDays.isEmpty()) {
//            return ResponseEntity.status(500).body("Hotel room is not available for selected dates");
        }

        orders.add(newOrder);
    }

    private List<LocalDate> daysBetween(LocalDate from, LocalDate to) {
        if (from.isAfter(to)) return Collections.emptyList();
        return from.datesUntil(to.plusDays(1)).toList();
    }

}

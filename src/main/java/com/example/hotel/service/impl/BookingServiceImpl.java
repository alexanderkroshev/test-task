package com.example.hotel.service.impl;

import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "database", havingValue = "real")
public class BookingServiceImpl implements BookingService {

    private final CommonBookingService commonBookingService;

    @Override
    @Transactional
    public boolean createOrder(OrderRequest newOrder) {
        return commonBookingService.createOrder(newOrder);
    }
}

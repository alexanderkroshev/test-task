package com.example.hotel.service.impl;


import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@ConditionalOnProperty(name = "database", havingValue = "mock")
public class MockBookingServiceImpl implements BookingService {

    private final CommonBookingService commonBookingService;

    @Override
    public boolean createOrder(OrderRequest newOrder) {
        return commonBookingService.createOrder(newOrder);
    }
}

package com.example.hotel.service;

import com.example.hotel.model.Order;
import com.example.hotel.model.request.OrderRequest;

import java.util.Optional;

public interface BookingService {

    Optional<Order> createOrder(OrderRequest request);


}

package com.example.hotel.rest;

import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest newOrder) {
        return ResponseEntity.status(201).body(orderService.createOrder(newOrder));
    }
}

package com.example.hotel.rest;

import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final BookingService bookingService;

    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest newOrder) {
        val isCreated = bookingService.createOrder(newOrder);
        if (isCreated) {
            return ResponseEntity.status(201).build();
        } else {
            return ResponseEntity.status(404).body("room is not available for specific date");
        }
    }
}

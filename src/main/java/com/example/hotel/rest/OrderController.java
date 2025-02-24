package com.example.hotel.rest;

import com.example.hotel.model.request.OrderRequest;
import com.example.hotel.model.response.OrderResponse;
import com.example.hotel.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest newOrder) {
        return bookingService.createOrder(newOrder)
                .map(order -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(new OrderResponse("Order created successfully", order)))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new OrderResponse("Room is not available for the selected date", null)));
    }
}


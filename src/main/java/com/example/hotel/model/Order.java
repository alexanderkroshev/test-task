package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
class Order {
    private String hotelId;
    private String roomId;
    private String userEmail;
    private LocalDate from;
    private LocalDate to;
}
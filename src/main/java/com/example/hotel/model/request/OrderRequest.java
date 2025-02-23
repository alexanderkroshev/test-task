package com.example.hotel.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequest {
    private Long roomId;
    private Long userId;
    private LocalDate from;
    private LocalDate to;
}

package com.example.hotel.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RoomAvailability {
    private Long id;
    private Long roomId;
    private LocalDate date;
    private int quota;

    public synchronized void decrementQuota() {
        if (quota > 0) {
            quota--;
        }
    }
}
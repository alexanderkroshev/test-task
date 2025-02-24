package com.example.hotel.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.concurrent.locks.ReentrantLock;

@Data
@NoArgsConstructor
public class RoomAvailability {
    private Long id;
    private Long roomId;
    private LocalDate date;
    private int quota;
    private ReentrantLock lock = new ReentrantLock();

    public RoomAvailability(Long id, Long roomId, LocalDate date, int quota) {
        this.id = id;
        this.roomId = roomId;
        this.date = date;
        this.quota = quota;
    }

    public void decrementQuota() {
        if (quota > 0) {
            quota--;
        }
    }

}
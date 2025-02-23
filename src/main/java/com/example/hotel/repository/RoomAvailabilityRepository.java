package com.example.hotel.repository;

import java.time.LocalDate;
import java.util.List;

public interface RoomAvailabilityRepository {
    boolean findByRoomAndDates(Long roomId, List<LocalDate> dates);
}

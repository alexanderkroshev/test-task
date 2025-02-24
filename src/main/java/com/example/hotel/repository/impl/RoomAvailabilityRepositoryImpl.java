package com.example.hotel.repository.impl;

import com.example.hotel.exception.MissingRoomDatesException;
import com.example.hotel.model.RoomAvailability;
import com.example.hotel.repository.RoomAvailabilityRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;

@Repository
@ConditionalOnProperty(name = "database", havingValue = "mock")
public class RoomAvailabilityRepositoryImpl implements RoomAvailabilityRepository {
    private final List<RoomAvailability> availability;

    public RoomAvailabilityRepositoryImpl() {
        availability = new CopyOnWriteArrayList<>(Arrays.asList(
                new RoomAvailability(1L, 1L, LocalDate.of(2024, 1, 1), 3),
                new RoomAvailability(2L, 1L, LocalDate.of(2024, 1, 2), 3),
                new RoomAvailability(3L, 1L, LocalDate.of(2024, 1, 3), 3),
                new RoomAvailability(4L, 1L, LocalDate.of(2024, 1, 4), 3),
                new RoomAvailability(5L, 1L, LocalDate.of(2024, 1, 5), 3)
        )
        );
    }

    @Override
    public boolean findByRoomAndDates(Long roomId, List<LocalDate> dates) {
        List<RoomAvailability> rooms = availability.stream()
                .filter(room -> Objects.equals(room.getRoomId(), roomId) &&
                        dates.contains(room.getDate()))
                .toList();

        if (rooms.size() != dates.size()) {
            throw new MissingRoomDatesException("Not all required dates are available for room:" + roomId);
        }

        rooms.forEach(room -> room.getLock().lock());
        try {
            boolean isAvailable = rooms.stream().allMatch(room -> room.getQuota() > 0);

            if (isAvailable) {
                rooms.forEach(RoomAvailability::decrementQuota);
                return true;
            }
            return false;
        } finally {
            rooms.forEach(room -> room.getLock().unlock());
        }
    }
}

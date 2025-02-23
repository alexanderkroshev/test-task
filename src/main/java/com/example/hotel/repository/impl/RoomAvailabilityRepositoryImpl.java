package com.example.hotel.repository.impl;

import com.example.hotel.model.RoomAvailability;
import com.example.hotel.repository.RoomAvailabilityRepository;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
@Repository
@ConditionalOnProperty(name = "database", havingValue = "mock")
public class RoomAvailabilityRepositoryImpl implements RoomAvailabilityRepository {

    private final ConcurrentMap<Long, RoomAvailability> availability = new ConcurrentHashMap<>();

    public RoomAvailabilityRepositoryImpl() {
        List<RoomAvailability> initialData = Arrays.asList(
                new RoomAvailability(1L, 1L, LocalDate.of(2024, 1, 1), 3),
                new RoomAvailability(2L, 1L, LocalDate.of(2024, 1, 2), 3),
                new RoomAvailability(3L, 1L, LocalDate.of(2024, 1, 3), 3),
                new RoomAvailability(4L, 1L, LocalDate.of(2024, 1, 4), 3),
                new RoomAvailability(5L, 1L, LocalDate.of(2024, 1, 5), 3)
        );

        for (RoomAvailability room : initialData) {
            availability.put(room.getId(), room);
        }
    }

    @Override
    public boolean findByRoomAndDates(Long roomId, List<LocalDate> dates) {
        List<RoomAvailability> rooms = availability.values().stream()
                .filter(room -> Objects.equals(room.getRoomId(), roomId) &&
                        dates.contains(room.getDate()))
                .toList();

        boolean isAvailable = rooms.stream().allMatch(room -> room.getQuota() > 0);

        if (isAvailable) {
            rooms.forEach(RoomAvailability::decrementQuota);
            return true;
        }
        return false;
    }
}

package com.example.hotel.repository.impl;

import com.example.hotel.model.RoomAvailability;
import com.example.hotel.repository.RoomAvailabilityRepository;
import lombok.val;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
@ConditionalOnProperty(name = "mock-db", havingValue = "true")
public class RoomAvailabilityRepositoryImpl implements RoomAvailabilityRepository {

    private final List<RoomAvailability> availability = new ArrayList<>(Arrays.asList(
            new RoomAvailability(1L, 1L, LocalDate.of(2024, 1, 1), 3),
            new RoomAvailability(2L, 1L, LocalDate.of(2024, 1, 2), 3),
            new RoomAvailability(3L, 1L, LocalDate.of(2024, 1, 3), 3),
            new RoomAvailability(4L, 1L, LocalDate.of(2024, 1, 4), 3),
            new RoomAvailability(5L, 1L, LocalDate.of(2024, 1, 5), 3)
    ));

    @Override
    public boolean findByRoomAndDates(Long roomId, List<LocalDate> dates) {
        Map<Long, Integer> idQuotaMap = availability.stream()
                .filter(room -> Objects.equals(room.getRoomId(), roomId) &&
                        dates.contains(room.getDate()))
                .collect(Collectors.toMap(RoomAvailability::getId, RoomAvailability::getQuota));

        val isAvailable = idQuotaMap.values().stream().allMatch(quota -> quota > 0);

        if (isAvailable) {
            for (RoomAvailability i : availability) {
                if (idQuotaMap.containsKey(i.getId())) {
                    i.setQuota(i.getQuota() - 1);
                }
            }
            return true;
        } else {
            return false;
        }
    }
}

package com.example.hotel.repository.impl;

import com.example.hotel.model.RoomAvailability;
import com.example.hotel.repository.OrderRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@ConditionalOnProperty(name = "mock-db", havingValue = "true")
public class OrderRepositoryMock implements OrderRepository {

    private final List<RoomAvailability> availability = new ArrayList<>(Arrays.asList(
            new RoomAvailability("reddison", "lux", LocalDate.of(2024, 1, 1), 1),
            new RoomAvailability("reddison", "lux", LocalDate.of(2024, 1, 2), 1),
            new RoomAvailability("reddison", "lux", LocalDate.of(2024, 1, 3), 1),
            new RoomAvailability("reddison", "lux", LocalDate.of(2024, 1, 4), 1),
            new RoomAvailability("reddison", "lux", LocalDate.of(2024, 1, 5), 0)
    ));

    public List<RoomAvailability> findAll() {
        return availability;
    }



}

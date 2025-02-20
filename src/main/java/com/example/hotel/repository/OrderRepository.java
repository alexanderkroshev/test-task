package com.example.hotel.repository;


import com.example.hotel.model.RoomAvailability;

import java.util.List;

public interface OrderRepository {

    List<RoomAvailability> findAll();
}

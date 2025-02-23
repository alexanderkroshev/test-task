package com.example.hotel.repository.impl;

import com.example.hotel.model.Order;
import com.example.hotel.repository.OrderRepository;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@ConditionalOnProperty(name = "database", havingValue = "mock")
public class OrderRepositoryImpl implements OrderRepository {
    private final List<Order> orders = new CopyOnWriteArrayList<>();
    private final AtomicLong orderIdGenerator = new AtomicLong(1);

    @Override
    public void save(Order order) {
        order.setId(orderIdGenerator.getAndIncrement());
        orders.add(order);
    }
}

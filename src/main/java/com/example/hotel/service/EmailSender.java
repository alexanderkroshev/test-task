package com.example.hotel.service;

import com.example.hotel.model.Order;

public interface EmailSender {

    void sendEmail(Order order);
}

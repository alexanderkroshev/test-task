package com.example.hotel.service.impl;

import com.example.hotel.model.Order;
import com.example.hotel.service.EmailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderImpl implements EmailSender {
    @Override
    public void sendEmail(Order order) {
        //here should be code which send message to message broker
    }
}

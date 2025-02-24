package com.example.hotel.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MissingRoomDatesException extends RuntimeException {

    public MissingRoomDatesException(String message) {
        super(message);
    }
}
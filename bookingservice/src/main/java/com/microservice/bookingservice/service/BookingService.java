package com.microservice.bookingservice.service;

import com.microservice.bookingservice.model.dtos.save.BookingDtoSave;
import com.microservice.bookingservice.model.dtos.send.BookingDtoSend;

import java.util.UUID;

public interface BookingService {
    BookingDtoSend save(BookingDtoSave bookingDtoSave);
    BookingDtoSend findById(UUID id);

    String cancelar(UUID id);
    String completar(UUID id);
    String fail(UUID id);
}

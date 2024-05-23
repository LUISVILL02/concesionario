package com.microservice.bookingservice.model.mapper;

import com.microservice.bookingservice.model.dtos.save.BookingDtoSave;
import com.microservice.bookingservice.model.dtos.send.BookingDtoSend;
import com.microservice.bookingservice.model.entities.Booking;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface BookingMapper {
    BookingDtoSave toDtoSave(Booking booking);
    Booking toEntity(BookingDtoSave bookingDtoSave);
    BookingDtoSend toDtoSend(Booking booking);
}

package com.microservice.bookingservice.repository;

import com.microservice.bookingservice.model.entities.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BookingRepository extends JpaRepository<Booking, UUID> {
}

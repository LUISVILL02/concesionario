package com.microservice.bookingservice.model.entities;

import com.microservice.bookingservice.model.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Locale;
import java.util.UUID;

@Entity
@Table(name = "bookins")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID idBooking;
    private UUID idCustomer;
    private UUID idCar;
    private LocalDate startDate;
    //acutalizar cuando se devuelva el  carro
    private LocalDate endDate;
    @Enumerated(EnumType.STRING)
    private BookingStatus status;
}

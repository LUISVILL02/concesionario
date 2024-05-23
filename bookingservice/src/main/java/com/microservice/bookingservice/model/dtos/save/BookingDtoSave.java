package com.microservice.bookingservice.model.dtos.save;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class BookingDtoSave {
    private UUID idCustomer;
    private UUID idCar;
}

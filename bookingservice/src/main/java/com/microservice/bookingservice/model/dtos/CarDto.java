package com.microservice.bookingservice.model.dtos;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDto {
    private UUID idCar;
    private String model;
    private String maker;
    private Boolean avaliable;
}

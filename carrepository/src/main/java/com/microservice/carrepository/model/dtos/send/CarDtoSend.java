package com.microservice.carrepository.model.dtos.send;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDtoSend {
    private UUID idCar;
    private String model;
    private String maker;
    private Boolean avaliable;
}

package com.microservice.carrepository.service;

import com.microservice.carrepository.model.dtos.save.CarDtoSave;
import com.microservice.carrepository.model.dtos.send.CarDtoSend;

import java.util.List;
import java.util.UUID;

public interface CarService {
    CarDtoSend saveCar(CarDtoSave carDtoSave);
    List<CarDtoSend> getAvaliableCars();

    CarDtoSend reserveCar(UUID idCar);
    CarDtoSend returnCar(UUID idCar);
}

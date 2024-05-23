package com.microservice.carrepository.model.mapper;

import com.microservice.carrepository.model.dtos.save.CarDtoSave;
import com.microservice.carrepository.model.dtos.send.CarDtoSend;
import com.microservice.carrepository.model.entitie.Car;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {
    CarDtoSave carToCarDtoSave(Car car);
    Car carDtoSaveToCar(CarDtoSave carDtoSave);
    CarDtoSend carToCarDtoSend(Car car);
    Car carDtoSendToCar(CarDtoSend carDtoSend);
}

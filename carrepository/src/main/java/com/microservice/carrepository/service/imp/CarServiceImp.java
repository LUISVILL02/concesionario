package com.microservice.carrepository.service.imp;

import com.microservice.carrepository.model.dtos.save.CarDtoSave;
import com.microservice.carrepository.model.dtos.send.CarDtoSend;
import com.microservice.carrepository.model.entitie.Car;
import com.microservice.carrepository.model.mapper.CarMapper;
import com.microservice.carrepository.repository.CarRepository;
import com.microservice.carrepository.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CarServiceImp implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    @Override
    public CarDtoSend saveCar(CarDtoSave carDtoSave) {
        Optional<Car> car = carRepository.findByModel(carDtoSave.getModel());
        if (car.isPresent()){
            throw new RuntimeException("Car already exists");
        }
        car = Optional.of(carMapper.carDtoSaveToCar(carDtoSave));
        car.get().setAvaliable(true);

        return carMapper.carToCarDtoSend(carRepository.save(car.get()));
    }

    @Override
    public List<CarDtoSend> getAvaliableCars() {
        return carRepository.findByAvaliableTrue().stream().map(carMapper::carToCarDtoSend).toList();
    }

    @Override
    public CarDtoSend reserveCar(UUID idCar) {
        Car car = carRepository.findById(idCar)
                .orElseThrow(() -> new RuntimeException("Car not found"));

        if(car.getAvaliable()){
            car.setAvaliable(false);
            return carMapper.carToCarDtoSend(carRepository.save(car));
        }
        throw new RuntimeException("Car not avaliable");
    }

    @Override
    public CarDtoSend returnCar(UUID idCar) {
        Car car = carRepository.findById(idCar)
                .orElseThrow(() -> new RuntimeException("Car not found"));
        if (!car.getAvaliable()){
            car.setAvaliable(true);
            return carMapper.carToCarDtoSend(carRepository.save(car));
        }
        throw new RuntimeException("Car already avaliable");
    }
}

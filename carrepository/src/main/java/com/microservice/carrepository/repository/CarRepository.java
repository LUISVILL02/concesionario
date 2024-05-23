package com.microservice.carrepository.repository;

import com.microservice.carrepository.model.entitie.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CarRepository extends JpaRepository<Car, UUID> {
    List<Car> findByAvaliableTrue();

    Optional<Car> findByModel(String model);
}

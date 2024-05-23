package com.microservice.carrepository.controller;

import com.microservice.carrepository.model.dtos.save.CarDtoSave;
import com.microservice.carrepository.model.dtos.send.CarDtoSend;
import com.microservice.carrepository.service.CarService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("microservice/carrepository")
@AllArgsConstructor
@Validated
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<CarDtoSend> saveCar(@RequestBody @Valid CarDtoSave carDtoSave){
        return ResponseEntity.created(URI.create("microservice/carrepository"))
                .body(carService.saveCar(carDtoSave));
    }

    @GetMapping
    public ResponseEntity<List<CarDtoSend>> getAvaliableCars(){
        return ResponseEntity.ok(carService.getAvaliableCars());
    }

    @PutMapping("/{idCar}")
    public ResponseEntity<CarDtoSend> reserveCar(@PathVariable UUID idCar){
        try {
            return ResponseEntity.ok(carService.reserveCar(idCar));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }

    @PatchMapping("/{idCar}")
    public ResponseEntity<CarDtoSend> returnCar(@PathVariable UUID idCar){
        try {
            return ResponseEntity.ok(carService.returnCar(idCar));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().build();
        }
    }
}

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
@RequestMapping("microservice/1.0.0./carrepository")
@AllArgsConstructor
@Validated
public class CarController {
    private final CarService carService;

    @PostMapping
    public ResponseEntity<?> saveCar(@RequestBody @Valid CarDtoSave carDtoSave){
        try {
            return ResponseEntity.created(URI.create("microservice/1.0.0./carrepository"))
                    .body(carService.saveCar(carDtoSave));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<CarDtoSend>> getAvaliableCars(){
        return ResponseEntity.ok(carService.getAvaliableCars());
    }

    @PutMapping("/{idCar}")
    public ResponseEntity<?> reserveCar(@PathVariable UUID idCar){
        try {
            return ResponseEntity.ok(carService.reserveCar(idCar));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{idCar}/{idBooking}")
    public ResponseEntity<?> returnCar(@PathVariable UUID idCar, @PathVariable UUID idBooking){
        try {
            return ResponseEntity.ok(carService.returnCar(idCar, idBooking));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}

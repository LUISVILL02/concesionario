package com.microservice.bookingservice.controller;

import com.microservice.bookingservice.model.dtos.save.BookingDtoSave;
import com.microservice.bookingservice.service.BookingService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/microservice/1.0.0./booking")
@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody BookingDtoSave bookingDtoSave) {
        try {
            return ResponseEntity.ok(bookingService.save(bookingDtoSave));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/cancelar/{id}")
    public ResponseEntity<?> cancel(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.cancelar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/completar/{id}")
    public ResponseEntity<?> complete(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.completar(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/fail/{id}")
    public ResponseEntity<?> fail(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(bookingService.fail(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}

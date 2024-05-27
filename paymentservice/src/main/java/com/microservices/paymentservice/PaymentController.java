package com.microservices.paymentservice;

import com.microservices.paymentservice.model.dtos.save.PaymentDtoSave;
import com.microservices.paymentservice.services.PaymentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("microservices/1.0.0./payment")
@AllArgsConstructor
@Validated
public class PaymentController {
    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<?> save(@Valid  @RequestBody PaymentDtoSave paymentDtoSave) {
        try {
            return ResponseEntity.ok(paymentService.save(paymentDtoSave));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable UUID id) {
        try {
            return ResponseEntity.ok(paymentService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/help/live")
    public ResponseEntity<?> findById() {
        return ResponseEntity.ok("OK");
    }
}

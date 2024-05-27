package com.microservice.customerservice.controller;

import com.microservice.customerservice.model.dtos.save.CustomerDtoSave;
import com.microservice.customerservice.model.dtos.send.CustomerDtoSend;
import com.microservice.customerservice.service.CustomerService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.UUID;

@RestController
@RequestMapping("/microservice/1.0.0./customers")
@AllArgsConstructor
@Validated
public class CustomerController {

    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerDtoSend> saveCustomer(@RequestBody @Valid CustomerDtoSave customerDtoSave){
        return ResponseEntity.created(URI.create("/microservice/1.0.0./customerservice"))
                .body(customerService.save(customerDtoSave));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCustomer(@PathVariable UUID id){
        try {
            return ResponseEntity.ok(customerService.findById(id)
                    .orElseThrow(() -> new RuntimeException("Customer not found")));
        } catch (RuntimeException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/help/live")
    public ResponseEntity<?> findById() {
        return ResponseEntity.ok("OK");
    }
}

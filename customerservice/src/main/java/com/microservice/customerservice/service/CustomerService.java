package com.microservice.customerservice.service;

import com.microservice.customerservice.model.dtos.save.CustomerDtoSave;
import com.microservice.customerservice.model.dtos.send.CustomerDtoSend;

import java.util.Optional;
import java.util.UUID;

public interface CustomerService {
    CustomerDtoSend save(CustomerDtoSave customerDtoSave);
    Optional<CustomerDtoSend> findById(UUID id);
}

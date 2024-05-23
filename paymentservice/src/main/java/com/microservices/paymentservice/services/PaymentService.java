package com.microservices.paymentservice.services;

import com.microservices.paymentservice.model.dtos.save.PaymentDtoSave;
import com.microservices.paymentservice.model.dtos.send.PaymentDtoSend;

import java.util.Optional;
import java.util.UUID;

public interface PaymentService {
    PaymentDtoSend save(PaymentDtoSave paymentDtoSave);
    Optional<PaymentDtoSend> findById(UUID id);
}

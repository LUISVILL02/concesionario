package com.microservices.paymentservice.model.mappers;

import com.microservices.paymentservice.model.dtos.save.PaymentDtoSave;
import com.microservices.paymentservice.model.dtos.send.PaymentDtoSend;
import com.microservices.paymentservice.model.entities.Payment;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    Payment  toEntity(PaymentDtoSave paymentDtoSave);
    PaymentDtoSend toDtoSend(Payment payment);

}

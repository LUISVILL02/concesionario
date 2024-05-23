package com.microservices.paymentservice.model.dtos.save;

import jakarta.validation.constraints.Min;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class PaymentDtoSave {
    private UUID idBooking;
    private Long creditCardNumber;
    @Min(value = 1, message = "Amount must be greater than 0")
    private Double amount;
}

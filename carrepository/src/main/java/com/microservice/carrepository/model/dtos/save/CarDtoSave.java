package com.microservice.carrepository.model.dtos.save;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CarDtoSave {
    @NotBlank(message = "Model is required")
    @Size(min = 3, max = 50, message = "Model must be between 3 and 50 characters")
    private String model;
    @NotBlank(message = "Maker is required")
    private String maker;
}

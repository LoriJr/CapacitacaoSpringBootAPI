package com.icaro.capacitacao.dto.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDTO {

    @NotBlank(message="The name field is required.")
    private String name;

    @NotBlank(message="The description field is required.")
    private String description;

    @Positive
    private BigDecimal price;
}

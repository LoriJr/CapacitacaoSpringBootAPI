package com.icaro.capacitacao.dto.order;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderItemRequestDTO {

    @NotNull(message = "Id Product is required.")
    private Long productId;

    @NotNull(message = "The quantity is required.")
    @Positive(message = "The quantity must be greater than zero.")
    private Integer quantity;
}

package com.icaro.capacitacao.dto.order;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequestDTO {

    @NotNull(message = "The user id  is required.")
    private Long userId;

    @NotNull(message = "The items list is required.")
    private List<OrderItemRequestDTO> items;
}

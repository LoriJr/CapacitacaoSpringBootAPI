package com.icaro.capacitacao.dto.order;


import com.icaro.capacitacao.dto.user.UserResponseDTO;
import com.icaro.capacitacao.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {

    private Long id;
    private Instant date;
    private OrderStatus orderStatus;
    private UserResponseDTO user;
    private List<OrderItemResponseDTO> items;
    private BigDecimal totalValue;
}

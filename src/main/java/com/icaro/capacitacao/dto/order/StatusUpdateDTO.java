package com.icaro.capacitacao.dto.order;

import com.icaro.capacitacao.enums.OrderStatus;
import lombok.Data;

@Data
public class StatusUpdateDTO {
    private OrderStatus orderStatus;
}

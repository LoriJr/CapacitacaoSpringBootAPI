package com.icaro.capacitacao.mapper;

import com.icaro.capacitacao.dto.order.OrderItemResponseDTO;
import com.icaro.capacitacao.model.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(target="productId", source="orderItem.product.id")
    @Mapping(target="productName", source="orderItem.product.name")
    @Mapping(target="subTotal", source="orderItem.subTotal")
    OrderItemResponseDTO toDTO(OrderItem orderItem);

    List<OrderItemResponseDTO> toDTO(List<OrderItem> items);
}

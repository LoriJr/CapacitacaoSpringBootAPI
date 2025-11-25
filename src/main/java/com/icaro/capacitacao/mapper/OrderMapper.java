package com.icaro.capacitacao.mapper;

import com.icaro.capacitacao.dto.order.OrderRequestDTO;
import com.icaro.capacitacao.dto.order.OrderResponseDTO;
import com.icaro.capacitacao.model.OrderEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;


@Mapper(componentModel = "spring", uses = {OrderItemMapper.class})
public interface OrderMapper {

    @Mapping(target="totalValue", source="orderEntity.totalValue")
    OrderResponseDTO toDTO(OrderEntity orderEntity);

    @Mapping(target="orderStatus" , ignore = true)
    void updateOrderPatch(OrderRequestDTO dto, @MappingTarget OrderEntity entity);
}

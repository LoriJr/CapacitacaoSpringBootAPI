package com.icaro.capacitacao.mapper;

import com.icaro.capacitacao.dto.product.ProductRequestDTO;
import com.icaro.capacitacao.dto.product.ProductResponseDTO;
import com.icaro.capacitacao.model.ProductEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDTO toDTO(ProductEntity productEntity);
    ProductEntity toEntity(ProductRequestDTO dto);

    void updateFromDTO(ProductRequestDTO dto, @MappingTarget ProductEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityPatch(ProductRequestDTO dto, @MappingTarget ProductEntity entity);
}

package com.icaro.capacitacao.mapper;

import com.icaro.capacitacao.dto.user.UserRequestDTO;
import com.icaro.capacitacao.dto.user.UserResponseDTO;
import com.icaro.capacitacao.model.UserEntity;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserResponseDTO toDTO(UserEntity userEntity);
    UserEntity toEntity(UserRequestDTO dto);

    void updateEntityFromDTO(UserRequestDTO dto, @MappingTarget UserEntity entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityPatch(UserRequestDTO dto, @MappingTarget UserEntity entity);
}

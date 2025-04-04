package com.sebastian.inventory_management.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sebastian.inventory_management.DTO.User.UserRequestDTO;
import com.sebastian.inventory_management.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movements", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);
    
    UserRequestDTO toDTO(User user);
}

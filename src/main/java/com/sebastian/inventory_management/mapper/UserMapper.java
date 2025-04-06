package com.sebastian.inventory_management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sebastian.inventory_management.DTO.User.UserRequestDTO;
import com.sebastian.inventory_management.DTO.User.UserResponseDTO;
import com.sebastian.inventory_management.model.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movements", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    User toEntity(UserRequestDTO userRequestDTO);

    UserResponseDTO toDTO(User user);

    List<UserResponseDTO> toDTOList(List<User> users);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "movements", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    void updateEntityFromDto(UserRequestDTO dto, @MappingTarget User entity);
}

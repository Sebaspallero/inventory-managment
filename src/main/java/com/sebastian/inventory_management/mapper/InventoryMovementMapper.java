package com.sebastian.inventory_management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementRequestDTO;
import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementResponseDTO;
import com.sebastian.inventory_management.model.InventoryMovement;

@Mapper(componentModel = "spring")
public interface InventoryMovementMapper {
    
    @Mapping(target = "id", ignore = true)
    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(target = "timestamp", expression = "java(java.time.LocalDateTime.now())")
    InventoryMovement toEntity(InventoryMovementRequestDTO dto);

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "product.name", target = "productName")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "user.username", target = "username")
    InventoryMovementResponseDTO toDTO(InventoryMovement movement);

    List<InventoryMovementResponseDTO> toDTOList(List<InventoryMovement> movements);
}

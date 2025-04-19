package com.sebastian.inventory_management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.sebastian.inventory_management.DTO.OrderItem.OrderItemRequestDTO;
import com.sebastian.inventory_management.DTO.OrderItem.OrderItemResponseDTO;
import com.sebastian.inventory_management.model.OrderItem;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "productId", target = "product.id")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "order", ignore = true)
    @Mapping(target = "price", ignore = true)
    OrderItem toEntity(OrderItemRequestDTO dto);

    @Mapping(source = "product.name", target = "productName")
    OrderItemResponseDTO toDTO(OrderItem orderItem);

    List<OrderItemResponseDTO> toDTOList(List<OrderItem> orderItems);

    List<OrderItem> toEntityList(List<OrderItemRequestDTO> dtos);
}

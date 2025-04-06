package com.sebastian.inventory_management.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import com.sebastian.inventory_management.DTO.Order.OrderRequestDTO;
import com.sebastian.inventory_management.DTO.Order.OrderResponseDTO;
import com.sebastian.inventory_management.model.Order;

@Mapper(componentModel = "spring", uses = { OrderItemMapper.class })
public interface OrderMapper {

    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "items", target = "items")
    @Mapping(target = "id", ignore = true)
    Order toEntity(OrderRequestDTO dto);

    @Mapping(source = "supplier.id", target = "supplierId")
    @Mapping(source = "supplier.name", target = "supplierName")
    @Mapping(source = "items", target = "items")
    OrderResponseDTO toDTO(Order order);

    List<OrderResponseDTO> toDTOList(List<Order> orders);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "supplierId", target = "supplier.id")
    @Mapping(source = "items", target = "items")
    void updateEntityFromDto(OrderRequestDTO dto, @MappingTarget Order order);
}

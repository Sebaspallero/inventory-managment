package com.sebastian.inventory_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.inventory_management.DTO.OrderItem.OrderItemResponseDTO;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.OrderItemMapper;
import com.sebastian.inventory_management.model.OrderItem;
import com.sebastian.inventory_management.repository.OrderItemRepository;
import com.sebastian.inventory_management.service.IOrderItemService;

@Service
public class OrderItemServiceImpl implements IOrderItemService {

    private OrderItemRepository orderItemRepository;
    private OrderItemMapper orderItemMapper;

    @Autowired
    public OrderItemServiceImpl(OrderItemRepository repository, OrderItemMapper mapper) {
        this.orderItemRepository = repository;
        this.orderItemMapper = mapper;
    }

    @Override
    @Transactional(readOnly = true)
    public OrderItemResponseDTO getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrderItem not found with id: " + id));

        return orderItemMapper.toDTO(orderItem);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderItemResponseDTO> findByOrderId(Long orderId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(orderId);
        return orderItemMapper.toDTOList(orderItems);
    }

    @Override
    public List<OrderItemResponseDTO> findByProductId(Long productId) {
        List<OrderItem> orderItems = orderItemRepository.findByProductId(productId);
        return orderItemMapper.toDTOList(orderItems);
    }
}

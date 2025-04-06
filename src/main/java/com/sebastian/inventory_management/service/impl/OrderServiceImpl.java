package com.sebastian.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.inventory_management.DTO.Order.OrderRequestDTO;
import com.sebastian.inventory_management.DTO.Order.OrderResponseDTO;
import com.sebastian.inventory_management.DTO.OrderItem.OrderItemRequestDTO;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.OrderItemMapper;
import com.sebastian.inventory_management.mapper.OrderMapper;
import com.sebastian.inventory_management.model.Order;
import com.sebastian.inventory_management.model.OrderItem;
import com.sebastian.inventory_management.model.Product;
import com.sebastian.inventory_management.model.Supplier;
import com.sebastian.inventory_management.repository.OrderRepository;
import com.sebastian.inventory_management.service.IOrderService;
import com.sebastian.inventory_management.service.IProductService;
import com.sebastian.inventory_management.service.ISupplierService;

@Service
public class OrderServiceImpl implements IOrderService {
    
    private final OrderRepository orderRepository;
    private final ISupplierService supplierService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final IProductService productService;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
                            ISupplierService supplierService,
                            OrderMapper orderMapper,
                            OrderItemMapper orderItemMapper,
                            IProductService productService) {
        this.orderRepository = orderRepository;
        this.supplierService = supplierService;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.productService = productService;
    }

    @Override
    public OrderResponseDTO createOrder(OrderRequestDTO orderDTO) {
        Supplier supplier = supplierService.getSupplierByIdEntity(orderDTO.getSupplierId());

        Order order = orderMapper.toEntity(orderDTO);

        order.setSupplier(supplier);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderNumber(generateOrderNumber());

        List<OrderItem> orderItems = mapOrderItems(orderDTO.getItems(), order);
        order.getItems().addAll(orderItems);

        Order savedOrder = orderRepository.save(order);
        return orderMapper.toDTO(savedOrder); 
    }

    @Override
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toDTO(order);
    }

    @Override
    public OrderResponseDTO getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with order number: " + orderNumber));
        return orderMapper.toDTO(order);
    }

    @Override
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDTOList(orders);
    }

    @Override
    public List<OrderResponseDTO> getOrdersBySupplier(Long supplierId) {
        Supplier supplier = supplierService.getSupplierByIdEntity(supplierId);
        List<Order> orders = orderRepository.findBySupplierId(supplier.getId());
        return orderMapper.toDTOList(orders);
    }

    @Override
    public List<OrderResponseDTO> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByOrderDateBetween(startDate, endDate);
        return orderMapper.toDTOList(orders);
    }

    @Override
    public OrderResponseDTO updateOrder(Long id, OrderRequestDTO orderDTO) {
        Order order = getOrderByIdEntity(id);
        Supplier supplier = supplierService.getSupplierByIdEntity(orderDTO.getSupplierId());

        order.setSupplier(supplier);
        order.setOrderDate(order.getOrderDate());
        order.setOrderNumber(order.getOrderNumber());

        List<OrderItem> orderItems = mapOrderItems(orderDTO.getItems(), order);
        order.getItems().clear();
        order.getItems().addAll(orderItems);

        return orderMapper.toDTO(orderRepository.save(order));
    }

    @Override
    public void deleteOrder(Long id) {
        Order order = getOrderByIdEntity(id);
        orderRepository.delete(order);
    }

    @Override
    public Order getOrderByIdEntity(Long id) {
        return orderRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));

    }

    private String generateOrderNumber() {
        return UUID.randomUUID().toString();
    }

    private List<OrderItem> mapOrderItems(List<OrderItemRequestDTO> itemDTOs, Order order) {
    return itemDTOs.stream().map(itemDTO -> {
        Product product = productService.getProductByIdEntity(itemDTO.getProductId());
        OrderItem orderItem = orderItemMapper.toEntity(itemDTO);
        orderItem.setProduct(product);
        orderItem.setOrder(order);
        return orderItem;
    }).collect(Collectors.toList());
}
}

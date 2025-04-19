package com.sebastian.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.inventory_management.DTO.Order.OrderCountByMonthDTO;
import com.sebastian.inventory_management.DTO.Order.OrderRequestDTO;
import com.sebastian.inventory_management.DTO.Order.OrderResponseDTO;
import com.sebastian.inventory_management.DTO.OrderItem.OrderItemRequestDTO;
import com.sebastian.inventory_management.enums.MovementType;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.OrderItemMapper;
import com.sebastian.inventory_management.mapper.OrderMapper;
import com.sebastian.inventory_management.model.InventoryMovement;
import com.sebastian.inventory_management.model.Order;
import com.sebastian.inventory_management.model.OrderItem;
import com.sebastian.inventory_management.model.Product;
import com.sebastian.inventory_management.model.Supplier;
import com.sebastian.inventory_management.model.User;
import com.sebastian.inventory_management.repository.InventoryMovementRepository;
import com.sebastian.inventory_management.repository.OrderRepository;
import com.sebastian.inventory_management.repository.ProductRepository;
import com.sebastian.inventory_management.service.IOrderService;
import com.sebastian.inventory_management.service.IProductService;
import com.sebastian.inventory_management.service.ISupplierService;
import com.sebastian.inventory_management.service.IUserService;

@Service
public class OrderServiceImpl implements IOrderService {

    private final OrderRepository orderRepository;
    private final ISupplierService supplierService;
    private final OrderMapper orderMapper;
    private final OrderItemMapper orderItemMapper;
    private final IProductService productService;
    private final IUserService userService;
    private final InventoryMovementServiceImpl movementService;
    private final ProductRepository productRepository;
    private final InventoryMovementRepository movementRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository,
            ISupplierService supplierService,
            OrderMapper orderMapper,
            OrderItemMapper orderItemMapper,
            IProductService productService,
            IUserService userService,
            InventoryMovementServiceImpl movementService,
            ProductRepository productRepository,
            InventoryMovementRepository movementRepository) {
        this.orderRepository = orderRepository;
        this.supplierService = supplierService;
        this.orderMapper = orderMapper;
        this.orderItemMapper = orderItemMapper;
        this.productService = productService;
        this.userService = userService;
        this.movementService = movementService;
        this.productRepository = productRepository;
        this.movementRepository = movementRepository;
    }

    @Override
    @Transactional
    public OrderResponseDTO createOrder(OrderRequestDTO orderDTO) {
        Supplier supplier = supplierService.getSupplierByIdEntity(orderDTO.getSupplierId());

        Order order = orderMapper.toEntity(orderDTO);
        order.setSupplier(supplier);
        order.setOrderDate(LocalDateTime.now());
        order.setOrderNumber(generateOrderNumber());

        List<OrderItem> orderItems = mapOrderItems(orderDTO.getItems(), order);

        order.setItems(orderItems);

        Order savedOrder = orderRepository.save(order);

        createInventoryMovementsForOrder(savedOrder);

        return orderMapper.toDTO(savedOrder);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with id: " + id));
        return orderMapper.toDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponseDTO getOrderByOrderNumber(String orderNumber) {
        Order order = orderRepository.findByOrderNumber(orderNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found with order number: " + orderNumber));
        return orderMapper.toDTO(order);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orderMapper.toDTOList(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getOrdersBySupplier(Long supplierId) {
        Supplier supplier = supplierService.getSupplierByIdEntity(supplierId);
        List<Order> orders = orderRepository.findBySupplierId(supplier.getId());
        return orderMapper.toDTOList(orders);
    }

    @Override
    @Transactional(readOnly = true)
    public List<OrderResponseDTO> getOrdersBetweenDates(LocalDateTime startDate, LocalDateTime endDate) {
        List<Order> orders = orderRepository.findByOrderDateBetween(startDate, endDate);
        return orderMapper.toDTOList(orders);
    }

    @Override
    public OrderCountByMonthDTO countOrdersByMonth() {
       return orderRepository.countOrdersByMonth();
    }

    @Override
    public List<OrderResponseDTO> findRecentOrders() {
        return orderRepository.findRecentOrders();
    }

    @Override
    @Transactional
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
    @Transactional
    public void deleteOrder(Long id) {
        Order order = getOrderByIdEntity(id);
        orderRepository.delete(order);
    }

    @Override
    @Transactional(readOnly = true)
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
            orderItem.setPrice(product.getPrice());
            return orderItem;
        }).collect(Collectors.toList());
    }

    private void createInventoryMovementsForOrder(Order order) {
        User user = userService.getCurrentUser();
        for (OrderItem item : order.getItems()) {
            movementService.updateStock(item.getProduct(), item.getQuantity(), MovementType.IN);

            InventoryMovement movement = InventoryMovement.builder()
                    .product(item.getProduct())
                    .quantity(item.getQuantity())
                    .type(MovementType.IN)
                    .user(user)
                    .timestamp(LocalDateTime.now())
                    .build();

            movementRepository.save(movement);
            productRepository.save(item.getProduct());
        }
    }

}

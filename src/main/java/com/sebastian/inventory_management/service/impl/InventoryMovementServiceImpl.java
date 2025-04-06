package com.sebastian.inventory_management.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementRequestDTO;
import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementResponseDTO;
import com.sebastian.inventory_management.enums.MovementType;
import com.sebastian.inventory_management.exception.InsufficientStockException;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.InventoryMovementMapper;
import com.sebastian.inventory_management.model.InventoryMovement;
import com.sebastian.inventory_management.model.Product;
import com.sebastian.inventory_management.model.User;
import com.sebastian.inventory_management.repository.InventoryMovementRepository;
import com.sebastian.inventory_management.repository.ProductRepository;
import com.sebastian.inventory_management.service.IInventoryMovementService;
import com.sebastian.inventory_management.service.IProductService;
import com.sebastian.inventory_management.service.IUserService;

@Service
public class InventoryMovementServiceImpl implements IInventoryMovementService {

    private final InventoryMovementRepository movementRepository;
    private final IProductService productService;
    private final IUserService userService;
    private final InventoryMovementMapper movementMapper;
    private final ProductRepository productRepository;

    @Autowired
    public InventoryMovementServiceImpl(InventoryMovementRepository movementRepository,
                                         IProductService productService,
                                         IUserService userService,
                                         InventoryMovementMapper movementMapper,
                                         ProductRepository productRepository) {
        this.movementRepository = movementRepository;
        this.productService = productService;
        this.userService = userService;
        this.movementMapper = movementMapper;
        this.productRepository = productRepository;
    }

    @Override
    @Transactional
    public InventoryMovementResponseDTO addMovement(InventoryMovementRequestDTO movement) {
        Product product = productService.getProductByIdEntity(movement.getProductId());
        User user = userService.getUserByIdEntity(movement.getUserId());

        InventoryMovement movementToSave = movementMapper.toEntity(movement);

        movementToSave.setProduct(product);
        movementToSave.setUser(user);

        int quantity = movement.getQuantity();
        updateStock(product, quantity, movement.getType());

        productRepository.save(product);
        InventoryMovement saved = movementRepository.save(movementToSave);
        return movementMapper.toDTO(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public InventoryMovementResponseDTO getMovementById(Long id) {
        InventoryMovement movement = movementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Movement not found with id: " + id));
        return movementMapper.toDTO(movement);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryMovementResponseDTO> getAllMovements() {
        List<InventoryMovement> movements = movementRepository.findAll();
        return movementMapper.toDTOList(movements);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryMovementResponseDTO> findByProductId(Long productId) {
        Product product = productService.getProductByIdEntity(productId);
        List<InventoryMovement> movements = movementRepository.findByProductId(product.getId());
        return movementMapper.toDTOList(movements);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryMovementResponseDTO> findByUserId(Long userId) {
        User user = userService.getUserByIdEntity(userId);
        List<InventoryMovement> movements = movementRepository.findByUserId(user.getId());
        return movementMapper.toDTOList(movements);
    }

    @Override
    @Transactional(readOnly = true)
    public List<InventoryMovementResponseDTO> findMovementsBetweenDates(LocalDateTime startDate,
            LocalDateTime endDate) {
        List<InventoryMovement> movements = movementRepository.findMovementsBetweenDates(startDate, endDate);
        return movementMapper.toDTOList(movements);
    }

    private void updateStock(Product product, int quantity, MovementType type) {
        if (type == MovementType.IN) {
            product.setStock(product.getStock() + quantity);
        } else if (type == MovementType.OUT) {
            if (product.getStock() < quantity) {
                throw new InsufficientStockException("Not enough stock to perform OUT movement");
            }
            product.setStock(product.getStock() - quantity);
        }
    }
}

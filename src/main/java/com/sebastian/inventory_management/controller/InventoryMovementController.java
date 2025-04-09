package com.sebastian.inventory_management.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementRequestDTO;
import com.sebastian.inventory_management.DTO.InventoryMovement.InventoryMovementResponseDTO;
import com.sebastian.inventory_management.service.IInventoryMovementService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inventory-movements")
public class InventoryMovementController {

    private IInventoryMovementService inventoryMovementService;

    @Autowired
    public InventoryMovementController(IInventoryMovementService inventoryMovementService) {
        this.inventoryMovementService = inventoryMovementService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<InventoryMovementResponseDTO> addMovement(
            @Valid @RequestBody InventoryMovementRequestDTO request) {
        InventoryMovementResponseDTO createdMovement = inventoryMovementService.addMovement(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovement);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/{id}")
    public ResponseEntity<InventoryMovementResponseDTO> getMovementById(@PathVariable Long id) {
        InventoryMovementResponseDTO movement = inventoryMovementService.getMovementById(id);
        return ResponseEntity.status(HttpStatus.OK).body(movement);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping
    public ResponseEntity<List<InventoryMovementResponseDTO>> getAllMovements() {
        List<InventoryMovementResponseDTO> movements = inventoryMovementService.getAllMovements();
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/product/{productId}")
    public ResponseEntity<List<InventoryMovementResponseDTO>> getMovementsByProduct(@PathVariable Long productId) {
        List<InventoryMovementResponseDTO> movements = inventoryMovementService.findByProductId(productId);
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InventoryMovementResponseDTO>> getMovementsByUser(@PathVariable Long userId) {
        List<InventoryMovementResponseDTO> movements = inventoryMovementService.findByUserId(userId);
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'EMPLOYEE')")
    @GetMapping("/between-dates")
    public ResponseEntity<List<InventoryMovementResponseDTO>> getMovementsBetweenDates(
            @RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
            @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end) {

        List<InventoryMovementResponseDTO> movements = inventoryMovementService.findMovementsBetweenDates(start, end);
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }

    @GetMapping("/monthly")
    public ResponseEntity<List<InventoryMovementResponseDTO>> getMonthlyInventoryMovements() {
        List<InventoryMovementResponseDTO> movements = inventoryMovementService.getMonthlyMovementsSummary();
        return ResponseEntity.status(HttpStatus.OK).body(movements);
    }
}

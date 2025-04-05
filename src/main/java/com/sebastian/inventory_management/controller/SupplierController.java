package com.sebastian.inventory_management.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.inventory_management.DTO.Supplier.SupplierRequestDTO;
import com.sebastian.inventory_management.DTO.Supplier.SupplierResponseDTO;
import com.sebastian.inventory_management.service.ISupplierService;

@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    private ISupplierService supplierService;

    @Autowired
    public SupplierController(ISupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<SupplierResponseDTO> createSupplier(@RequestBody SupplierRequestDTO supplierRequest) {
        SupplierResponseDTO createdSupplier = supplierService.saveSupplier(supplierRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSupplier);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> getSupplierById(@PathVariable Long id){
        SupplierResponseDTO supplier = supplierService.getSupplierById(id);
        return ResponseEntity.status(HttpStatus.OK).body(supplier);
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByName(@PathVariable String name){
        SupplierResponseDTO supplier = supplierService.getSupplierByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(supplier);
    }

    @GetMapping("/contact-email/{contactEmail}")
    public ResponseEntity<SupplierResponseDTO> getSupplierByContactEmail(@PathVariable String contactEmail){
        SupplierResponseDTO supplier = supplierService.getSupplierByContactEmail(contactEmail);
        return ResponseEntity.status(HttpStatus.OK).body(supplier);
    }

    @GetMapping
    public ResponseEntity<List<SupplierResponseDTO>> getAllSuppliers(){
        List<SupplierResponseDTO> suppliers = supplierService.getAllSuppliers();
        return ResponseEntity.status(HttpStatus.OK).body(suppliers);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSupplier(@PathVariable Long id){
        supplierService.deleteSupplier(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<SupplierResponseDTO> updateSupplier(@PathVariable Long id, @RequestBody SupplierRequestDTO supplierRequest) {
        SupplierResponseDTO updatedSupplier = supplierService.updateSupplier(id, supplierRequest);
        return ResponseEntity.status(HttpStatus.OK).body(updatedSupplier);
    }
}

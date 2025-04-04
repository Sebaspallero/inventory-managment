package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.DTO.Supplier.SupplierRequestDTO;
import com.sebastian.inventory_management.DTO.Supplier.SupplierResponseDTO;

public interface ISupplierService {
    SupplierResponseDTO saveSupplier(SupplierRequestDTO supplier);
    SupplierResponseDTO getSupplierById(Long id);
    SupplierResponseDTO getSupplierByName(String name);
    SupplierResponseDTO getSupplierByContactEmail(String contactEmail);
    List<SupplierResponseDTO> getAllSuppliers();
    void deleteSupplier(Long id);
    SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO supplier);
}

package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.Supplier;

public interface ISupplierService {
    Supplier saveSupplier(Supplier supplier);
    Supplier updateSupplier(Long id, Supplier supplier);
    void deleteSupplier(Long id);
    Supplier getSupplierById(Long id);
    Supplier getSupplierByName(String name);
    Supplier getSupplierByContactEmail(String contactEmail);
    List<Supplier> getAllSuppliers();
}

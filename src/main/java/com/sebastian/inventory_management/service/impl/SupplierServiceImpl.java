package com.sebastian.inventory_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sebastian.inventory_management.DTO.Supplier.SupplierRequestDTO;
import com.sebastian.inventory_management.DTO.Supplier.SupplierResponseDTO;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.SupplierMapper;
import com.sebastian.inventory_management.model.Supplier;
import com.sebastian.inventory_management.repository.SupplierRepository;
import com.sebastian.inventory_management.service.ISupplierService;

@Service
public class SupplierServiceImpl implements ISupplierService{

    private SupplierRepository supplierRepository;
    private SupplierMapper supplierMapper;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    @Override
    public SupplierResponseDTO saveSupplier(SupplierRequestDTO supplier) {
        validateUniqueSupplier(supplier.getName(), supplier.getContactEmail(), null);
        Supplier supplierToSave = supplierMapper.toEntity(supplier);
        Supplier savedSupplier = supplierRepository.save(supplierToSave);
        return supplierMapper.toDTO(savedSupplier);
    }

    @Override
    public SupplierResponseDTO getSupplierById(Long id) {
        Supplier supplier = supplierRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public SupplierResponseDTO getSupplierByName(String name) {
        Supplier supplier = supplierRepository.findByName(name)
            .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with name: " + name));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public SupplierResponseDTO getSupplierByContactEmail(String contactEmail) {
        Supplier supplier = supplierRepository.findByContactEmail(contactEmail)
            .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with contact email: " + contactEmail));
        return supplierMapper.toDTO(supplier);
    }

    @Override
    public List<SupplierResponseDTO> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        return supplierMapper.toDTOList(suppliers);
    }

    @Override
    public void deleteSupplier(Long id) {
        Supplier supplier = getSupplierByIdEntity(id);
        supplierRepository.delete(supplier);
    }

    @Override
    public SupplierResponseDTO updateSupplier(Long id, SupplierRequestDTO supplier) {
        Supplier supplierToUpdate = getSupplierByIdEntity(id);
        validateUniqueSupplier(supplier.getName(), supplier.getContactEmail(), id);
        supplierMapper.updateEntityFromDto(supplier, supplierToUpdate);
        supplierRepository.save(supplierToUpdate);
        return supplierMapper.toDTO(supplierToUpdate);
    }

    @Override
    public boolean existsById(Long id) {
        return supplierRepository.existsById(id);
    }

    @Override
    public Supplier getSupplierByIdEntity(Long id) {
        return supplierRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Supplier not found with id: " + id));
    }

    private void validateUniqueSupplier(String name, String contactEmail, Long excludeId) {
        supplierRepository.findByName(name).ifPresent(existing -> {
            if (excludeId == null || !existing.getId().equals(excludeId)) {
                throw new IllegalArgumentException("Supplier with name '" + name + "' already exists.");
            }
        });
    
        supplierRepository.findByContactEmail(contactEmail).ifPresent(existing -> {
            if (excludeId == null || !existing.getId().equals(excludeId)) {
                throw new IllegalArgumentException("Supplier with contact email '" + contactEmail + "' already exists.");
            }
        });
    }
}

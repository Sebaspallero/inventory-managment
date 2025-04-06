package com.sebastian.inventory_management.DTO.Supplier;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierResponseDTO {
   
    private Long id;
    private String name;
    private String contactEmail;
    private String phoneNumber;
}

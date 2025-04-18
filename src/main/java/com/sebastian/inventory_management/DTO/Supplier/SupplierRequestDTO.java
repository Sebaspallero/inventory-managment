package com.sebastian.inventory_management.DTO.Supplier;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SupplierRequestDTO {
    
    @NotBlank(message = "Name is mandatory")
    private String name;

    @NotBlank(message = "Contact email is mandatory")
    @Email(message = "Invalid email format") 
    private String contactEmail;

    @NotBlank(message = "Phone number is mandatory")
    @Pattern(regexp = "\\d{10,15}", message = "Invalid phone number")
    private String phoneNumber;
}

package com.sebastian.inventory_management.DTO.Category;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CategoryRequestDTO {

    @NotBlank(message = "Name is mandatory")
    @Size(max = 100, message = "Name must be less than 100 characters")
    private String name;

}

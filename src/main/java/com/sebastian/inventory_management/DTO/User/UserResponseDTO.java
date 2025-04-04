package com.sebastian.inventory_management.DTO.User;

import com.sebastian.inventory_management.enums.Role;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserResponseDTO {
    
    private Long id;
    private String username;
    private String email;
    private Role role;
    private boolean enabled;
}

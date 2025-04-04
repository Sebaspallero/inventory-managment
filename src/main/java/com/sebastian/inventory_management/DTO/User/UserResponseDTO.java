package com.sebastian.inventory_management.DTO.User;

import com.sebastian.inventory_management.enums.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDTO {
    
    private Long id;
    private String username;
    private String email;
    private Role role;
    private boolean enabled;
}

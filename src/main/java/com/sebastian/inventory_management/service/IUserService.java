package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.DTO.User.UserRequestDTO;
import com.sebastian.inventory_management.DTO.User.UserResponseDTO;
import com.sebastian.inventory_management.model.User;

public interface IUserService {
    UserResponseDTO addUser(UserRequestDTO user);
    UserResponseDTO updateUser(Long id, UserRequestDTO user);
    void deleteUser(Long id);
    UserResponseDTO getUserById(Long id);
    UserResponseDTO getUserByUsername(String username);
    UserResponseDTO getUserByEmail(String email);
    List<UserResponseDTO> getAllUsers();
    User getUserByIdEntity(Long id);
}

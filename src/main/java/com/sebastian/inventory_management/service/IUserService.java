package com.sebastian.inventory_management.service;

import java.util.List;

import com.sebastian.inventory_management.model.User;

public interface IUserService {
    User addUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
    User getUserById(Long id);
    List<User> getAllUsers();
}

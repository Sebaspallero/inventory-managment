package com.sebastian.inventory_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebastian.inventory_management.DTO.User.UserRequestDTO;
import com.sebastian.inventory_management.DTO.User.UserResponseDTO;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.UserMapper;
import com.sebastian.inventory_management.model.User;
import com.sebastian.inventory_management.repository.UserRepository;
import com.sebastian.inventory_management.service.IUserService;

@Service
public class UserServiceImpl implements IUserService{
    
    private UserRepository userRepository;
    private UserMapper userMapper;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    @Transactional
    public UserResponseDTO addUser(UserRequestDTO user) {
        User userToSave = userMapper.toEntity(user);
        User savedUser = userRepository.save(userToSave);
        return userMapper.toDTO(savedUser);
    }
    
    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserById(Long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional
    public UserResponseDTO updateUser(Long id, UserRequestDTO user) {
        User userToUpdate = getUserByIdAux(id);
        userMapper.updateEntityFromDto(user, userToUpdate);
        userRepository.save(userToUpdate);
        return userMapper.toDTO(userToUpdate);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserByIdAux(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByUsername(String username) {
        User user = userRepository.findByUsername(username)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with username: " + username));
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResponseDTO getUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + email));
        return userMapper.toDTO(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTOList(users);
    }

    private User getUserByIdAux(Long id) {
        return userRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }
}

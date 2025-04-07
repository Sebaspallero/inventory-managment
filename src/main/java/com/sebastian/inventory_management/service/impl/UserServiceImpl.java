package com.sebastian.inventory_management.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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
public class UserServiceImpl implements IUserService, UserDetailsService {

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
        User userToUpdate = getUserByIdEntity(id);
        userMapper.updateEntityFromDto(user, userToUpdate);
        userRepository.save(userToUpdate);
        return userMapper.toDTO(userToUpdate);
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {
        User user = getUserByIdEntity(id);
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

    @Override
    @Transactional(readOnly = true)
    public User getUserByIdEntity(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))
        );
    }

    @Override
    public User getCurrentUser() {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + email));
    }
}

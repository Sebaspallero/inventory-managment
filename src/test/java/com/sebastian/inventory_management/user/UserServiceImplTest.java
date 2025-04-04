package com.sebastian.inventory_management.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.sebastian.inventory_management.DTO.User.UserRequestDTO;
import com.sebastian.inventory_management.DTO.User.UserResponseDTO;
import com.sebastian.inventory_management.enums.Role;
import com.sebastian.inventory_management.exception.ResourceNotFoundException;
import com.sebastian.inventory_management.mapper.UserMapper;
import com.sebastian.inventory_management.model.User;
import com.sebastian.inventory_management.repository.UserRepository;
import com.sebastian.inventory_management.service.impl.UserServiceImpl;


@ExtendWith(org.mockito.junit.jupiter.MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;
    private UserRequestDTO userRequestDTO;
    private UserResponseDTO userResponseDTO;

    @BeforeEach
    void setUp() {
        userRequestDTO = new UserRequestDTO("testuser", "test@example.com", "password123", Role.ADMIN);
        user = User.builder()
                .id(1L)
                .username("testuser")
                .email("test@example.com")
                .password("password123")
                .role(Role.ADMIN)
                .enabled(true)
                .movements(new ArrayList<>())
                .build();
        userResponseDTO = new UserResponseDTO(1L, "testuser", "test@example.com", Role.ADMIN, true);
    }

    @Test
    void testAddUser() {
        when(userMapper.toEntity(userRequestDTO)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toDTO(user)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.addUser(userRequestDTO);

        assertEquals(userResponseDTO, result);
        verify(userRepository).save(user);
    }

    @Test
    void testGetUserById_WhenUserExists() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userMapper.toDTO(user)).thenReturn(userResponseDTO);

        UserResponseDTO result = userService.getUserById(1L);

        assertEquals(userResponseDTO, result);
    }

    @Test
    void testGetUserById_WhenUserNotFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> userService.getUserById(1L));
    }

    @Test
    void testDeleteUser() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        doNothing().when(userRepository).delete(user);

        assertDoesNotThrow(() -> userService.deleteUser(1L));
        verify(userRepository).delete(user);
    }

    @Test
    void testGetAllUsers() {
        List<User> userList = List.of(user);
        List<UserResponseDTO> dtoList = List.of(userResponseDTO);

        when(userRepository.findAll()).thenReturn(userList);
        when(userMapper.toDTOList(userList)).thenReturn(dtoList);

        List<UserResponseDTO> result = userService.getAllUsers();

        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
    }
}


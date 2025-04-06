package com.sebastian.inventory_management.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sebastian.inventory_management.DTO.Auth.AuthenticationRequest;
import com.sebastian.inventory_management.DTO.Auth.AuthenticationResponse;
import com.sebastian.inventory_management.DTO.Auth.RegisterRequest;
import com.sebastian.inventory_management.model.User;
import com.sebastian.inventory_management.repository.UserRepository;
import com.sebastian.inventory_management.service.IAuthService;

@Service
public class AuthService implements IAuthService {

    private AuthenticationManager authenticationManager;
    private JwtService jwtService;
    private UserDetailsService userDetailsService;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtService jwtService,
            UserDetailsService userDetailsService, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;

    }

    @Override
    public AuthenticationResponse login(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));

        UserDetails user = userDetailsService.loadUserByUsername(request.getEmail());
        String token = jwtService.generateToken(user);
        return new AuthenticationResponse(token, user.getUsername(), user.getAuthorities().toString());
    }

    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email already in use");
        }

        User user = new User();
        user.setUsername(request.getUsername());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole());

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken, user.getUsername(), user.getAuthorities().toString());
    }

}

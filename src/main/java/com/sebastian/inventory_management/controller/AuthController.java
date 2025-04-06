package com.sebastian.inventory_management.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebastian.inventory_management.DTO.Auth.AuthenticationRequest;
import com.sebastian.inventory_management.DTO.Auth.AuthenticationResponse;
import com.sebastian.inventory_management.DTO.Auth.RegisterRequest;
import com.sebastian.inventory_management.security.AuthService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authenticationService;

    @Autowired
    public AuthController(AuthService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@Valid @RequestBody AuthenticationRequest request) {
        AuthenticationResponse response = authenticationService.login(request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthenticationResponse response = authenticationService.register(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}

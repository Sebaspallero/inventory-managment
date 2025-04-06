package com.sebastian.inventory_management.service;

import com.sebastian.inventory_management.DTO.Auth.AuthenticationRequest;
import com.sebastian.inventory_management.DTO.Auth.AuthenticationResponse;

public interface IAuthService {

    AuthenticationResponse login(AuthenticationRequest request);
}

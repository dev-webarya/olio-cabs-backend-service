package com.oliocabs.service;

import com.oliocabs.dto.request.LoginRequest;
import com.oliocabs.dto.request.RefreshTokenRequest;
import com.oliocabs.dto.request.RegisterRequest;
import com.oliocabs.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    /**
     * Registers a new user in the system.
     * @param request DTO containing user registration details.
     * @return AuthenticationResponse containing access and refresh tokens.
     */
    AuthenticationResponse register(RegisterRequest request);

    /**
     * Authenticates a user and provides tokens.
     * @param request DTO containing user login credentials.
     * @return AuthenticationResponse containing access and refresh tokens.
     */
    AuthenticationResponse login(LoginRequest request);

    /**
     * Refreshes an expired access token using a refresh token.
     * @param request DTO containing the refresh token.
     * @return AuthenticationResponse containing a new access token and the original refresh token.
     */
    AuthenticationResponse refreshToken(RefreshTokenRequest request);
}
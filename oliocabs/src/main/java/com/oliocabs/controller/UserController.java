package com.oliocabs.controller;

import com.oliocabs.dto.request.UserRequest;
import com.oliocabs.entity.User;
import com.oliocabs.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    // We need the password encoder to hash the password on creation
    private final PasswordEncoder passwordEncoder;

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody UserRequest request) {
        // NOTE: In a real app, this logic would be in a UserService
        User user = User.builder()
                .firstName(request.getFirstName()) // MODIFIED: Was .name()
                .lastName(request.getLastName())   // MODIFIED: Added this line
                .username(request.getEmail())      // The username field in the entity holds the email
                .password(passwordEncoder.encode(request.getPassword()))
                // Role will be set by the Register endpoint in AuthController.
                // This is just a simple user creation for testing.
                .build();
        User savedUser = userRepository.save(user);

        // Don't return the full user object with password hash
        savedUser.setPassword(null);

        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }
}
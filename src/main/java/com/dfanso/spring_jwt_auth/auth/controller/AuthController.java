package com.dfanso.spring_jwt_auth.auth.controller;


import com.dfanso.spring_jwt_auth.auth.dto.*;
import com.dfanso.spring_jwt_auth.auth.exception.EmailAlreadyTakenException;
import com.dfanso.spring_jwt_auth.auth.exception.InvalidCredentialsException;
import com.dfanso.spring_jwt_auth.auth.exception.ResourceNotFoundException;
import com.dfanso.spring_jwt_auth.auth.service.AuthService;
import com.dfanso.spring_jwt_auth.auth.service.UserDetailsImpl;
import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserDto userDto) {
        try {
            UserRegistrationResponseDto registeredUser = authService.registerUser(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
        } catch (EmailAlreadyTakenException e) {
            ErrorResponseDto errorResponse = new ErrorResponseDto(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> loginUser(@RequestBody LoginRequestDto loginRequestDto) {
        try {
            String token = authService.authenticateUser(loginRequestDto.getEmail(), loginRequestDto.getPassword());
            return ResponseEntity.ok(new LoginResponseDto(token, "Login successful"));
        } catch (InvalidCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponseDto(null, "Invalid credentials"));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new LoginResponseDto(null, e.getMessage()));
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getProfile(Authentication authentication) {
        // Get the user information from the authenticated principal
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Create a response object with the user information
        ProfileResponse profileResponse = new ProfileResponse(
                userDetails.getEmail(),
                userDetails.getFirstName(),
                userDetails.getLastName()
        );

        return ResponseEntity.ok(profileResponse);
    }
}

package com.dfanso.spring_jwt_auth.auth.controller;


import com.dfanso.spring_jwt_auth.auth.dto.UserDto;
import com.dfanso.spring_jwt_auth.auth.model.User;
import com.dfanso.spring_jwt_auth.auth.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto) {
        UserDto registeredUser = userService.registerUser(userDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(@RequestBody UserDto userDto) {
        boolean isAuthenticated = userService.authenticateUser(userDto.getEmail(), userDto.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }
}

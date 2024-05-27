package com.dfanso.spring_jwt_auth.auth.service;


import com.dfanso.spring_jwt_auth.auth.dto.UserDto;
import com.dfanso.spring_jwt_auth.auth.security.jwtUtils;
import com.dfanso.spring_jwt_auth.auth.dto.UserRegistrationResponseDto;
import com.dfanso.spring_jwt_auth.auth.exception.EmailAlreadyTakenException;
import com.dfanso.spring_jwt_auth.auth.exception.InvalidCredentialsException;
import com.dfanso.spring_jwt_auth.auth.exception.ResourceNotFoundException;
import com.dfanso.spring_jwt_auth.auth.model.User;
import com.dfanso.spring_jwt_auth.auth.repository.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;

    private final com.dfanso.spring_jwt_auth.auth.security.jwtUtils jwtUtils;

    @Autowired
    public AuthService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder, ModelMapper modelMapper, jwtUtils jwtUtils) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.modelMapper = modelMapper;
        this.jwtUtils = jwtUtils;
    }

    public UserRegistrationResponseDto registerUser(UserDto userDto) {
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new EmailAlreadyTakenException(userDto.getEmail());
        }

        User user = modelMapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User registeredUser = userRepository.save(user);
        return modelMapper.map(registeredUser, UserRegistrationResponseDto.class);
    }


    public UserDto findUserByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));
        return modelMapper.map(user, UserDto.class);
    }

    public String authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("User", "email", email));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }

        // Generate JWT token
        UserDetailsImpl userDetails = UserDetailsImpl.build(user);
        String token = jwtUtils.generateJwtToken(userDetails);

        return token;
    }
}
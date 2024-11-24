package com.shareskills.api.service;

import com.shareskills.api.exception.BadRequestException;
import com.shareskills.api.model.AuthRequest;
import com.shareskills.api.model.AuthResponse;
import com.shareskills.api.model.Role;
import com.shareskills.api.model.User;
import com.shareskills.api.model.dto.UserDTO;
import com.shareskills.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    JwtService jwtService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    public User register(AuthRequest authRequest) throws BadRequestException {
        if (userRepository.existsByEmail(authRequest.getEmail())) {
            throw new BadRequestException("Email already exists");
        }
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(authRequest.getEmail());
        userDTO.setPassword(bCryptPasswordEncoder.encode(authRequest.getPassword()));
        userDTO.setRoles(List.of(Role.ROLE_USER));

        return userService.createUser(userDTO);
    }

    public AuthResponse authResponse(User user, String username) {
        user.setRefreshToken(jwtService.createRefreshToken());
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtService.generateToken(user));
        authResponse.setRefreshToken(user.getRefreshToken());
        authResponse.setUserId(String.valueOf(user.getId()));
        authResponse.setUsername(user.getEmail());
        authResponse.setRoles(user.getRoles());
        return authResponse;
    }
}

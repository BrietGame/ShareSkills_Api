package com.shareskills.api.controller;

import com.shareskills.api.exception.BadRequestException;
import com.shareskills.api.model.AuthRequest;
import com.shareskills.api.model.AuthResponse;
import com.shareskills.api.model.User;
import com.shareskills.api.repository.UserRepository;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.AuthService;
import com.shareskills.api.service.JwtService;
import com.shareskills.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import java.nio.file.AccessDeniedException;
import java.util.Objects;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private System.Logger logger = System.getLogger(AuthController.class.getName());

    @Autowired
    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseJson<User> register(@RequestBody AuthRequest authRequest) throws BadRequestException {
        logger.log(System.Logger.Level.INFO, "Registering user: " + authRequest.getEmail());
        if (authRequest.getEmail() == null) {
            throw new BadRequestException("Email not found");
        }
        return new ResponseJson<>(authService.register(authRequest), HttpStatus.OK.value());
    }

    @PostMapping("/login")
    public ResponseJson<AuthResponse> authenticateAndGetToken(@RequestBody AuthRequest authRequest) throws AccountNotFoundException, AccessDeniedException {
        logger.log(System.Logger.Level.INFO, "Authenticating user: " + authRequest.getEmail());
        // Trouver l'utilisateur par son login (username ou email)
        User user = userService.getUserByEmail(authRequest.getEmail());
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getEmail(), authRequest.getPassword())
            );
        } catch (Exception e) {
            throw new BadRequestException("Invalid credentials");
        }
        if (authRequest.getRefreshToken() != null) {
            if (Objects.requireNonNull(user).getRefreshToken().equals(authRequest.getRefreshToken())) {
                throw new AccessDeniedException("Refresh token not found");
            }
        }
        user.setRefreshToken(jwtService.createRefreshToken());
        userRepository.save(user);
        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwtToken(jwtService.generateToken(user));
        authResponse.setRefreshToken(user.getRefreshToken());
        authResponse.setUserId(String.valueOf(user.getId()));
        authResponse.setUsername(user.getEmail());
        authResponse.setRoles(user.getRoles());
        return new ResponseJson<>(authResponse, HttpStatus.OK.value(), jwtService.getExpiration());
    }

    @PostMapping("/refresh")
    public ResponseJson<AuthResponse> refreshToken(@RequestBody AuthRequest authRequest) throws AccessDeniedException {
        logger.log(System.Logger.Level.INFO, "Refreshing token for user: " + authRequest.getEmail());
        if (authRequest.getRefreshToken() == null && authRequest.getEmail() == null) {
            throw new AccessDeniedException("Invalid credentials");
        }
        User user = userService.getUserByEmail(authRequest.getEmail());
        if (!user.getRefreshToken().equals(authRequest.getRefreshToken())) {
            throw new AccessDeniedException("Refresh token invalid");
        }
        return new ResponseJson<>(authService.authResponse(user, authRequest.getEmail()), HttpStatus.OK.value(), jwtService.getExpiration());
    }
}
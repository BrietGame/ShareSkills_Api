package com.shareskills.api.model;

import lombok.Data;

import java.util.List;

@Data
public class AuthResponse {
    public String jwtToken;

    public String refreshToken;

    public String userId;

    public String username;

    public List<Role> roles;
}
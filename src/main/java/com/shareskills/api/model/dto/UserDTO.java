package com.shareskills.api.model.dto;

import com.shareskills.api.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;
    private String email;
    private String password;
    public List<Role> roles;
    private String status;
    private String refreshToken;
}

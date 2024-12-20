package com.shareskills.api.model.dto;

import com.shareskills.api.model.Role;
import jakarta.validation.constraints.Email;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long id;

    @Email(message = "L'email de l'utilisateur est invalide")
    private String email;
    private String password;

    public List<Role> roles = List.of(Role.ROLE_USER);
    private String status;
    private String refreshToken;
}

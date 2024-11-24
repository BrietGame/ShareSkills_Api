package com.shareskills.api.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id")
    private Long id;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "zip")
    private Integer zip;

    @Column(name = "city")
    private String city;

    @Column(name = "country")
    private String country;

    @Column(name = "avatar_url")
    private String avatarUrl;

    @Column(name = "roles")
    public List<Role> roles;

    @Column(name = "status")
    private String status;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "created_at")
    private ZonedDateTime createdAt = ZonedDateTime.now();

    @Column(name = "updated_at")
    private ZonedDateTime updatedAt = ZonedDateTime.now();
}

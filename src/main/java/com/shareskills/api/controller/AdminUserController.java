package com.shareskills.api.controller;

import com.shareskills.api.model.User;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseJson<List<User>> getUsers() {
        return new ResponseJson<>(userService.getAllUsers(), HttpStatus.OK.value());
    }
}

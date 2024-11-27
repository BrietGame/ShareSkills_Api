package com.shareskills.api.controller;

import com.shareskills.api.model.User;
import com.shareskills.api.response.ResponseJson;
import com.shareskills.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/admin/user")
public class AdminUserController {

    @Autowired
    private UserService userService;

    @GetMapping("/list")
    public ResponseEntity<ResponseJson<List<User>>> getUsers() {
        List<User> users = userService.getAllUsers();
        ResponseJson<List<User>> response = new ResponseJson<>(users, HttpStatus.OK.value());
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseJson<User>> getUserById(@PathVariable String id) {
        Optional<User> optionalUser = userService.getUserById(id);
        ResponseJson<User> response = optionalUser.map(user -> new ResponseJson<>(user, HttpStatus.OK.value())).orElseGet(() -> new ResponseJson<>(null, HttpStatus.NOT_FOUND.value()));
        return ResponseEntity.status(response.getStatusCode()).body(response);
    }
}

package com.enigma.grooming.controller;

import com.enigma.grooming.model.User;
import com.enigma.grooming.model.response.CommonResponse;
import com.enigma.grooming.model.response.UserByIdResponse;
import com.enigma.grooming.model.response.UserResponse;
import com.enigma.grooming.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<UserResponse> getAll() {
        List<User> users = userService.findAll();
        UserResponse resp = new UserResponse(users);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable(name = "id") String id) {
        User user = userService.findById(id).get();
        UserByIdResponse response = new UserByIdResponse(user);
        return ResponseEntity.ok(response);
    }
}

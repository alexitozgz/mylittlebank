package com.iobuilders.mylittebank.interfaces.rest;

import com.iobuilders.mylittebank.domain.model.User;
import com.iobuilders.mylittebank.domain.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody User userRequest) {
        User user = userService.registerUser(userRequest.getName(), userRequest.getPhoneNumber(), userRequest.getEmail());
        return ResponseEntity.ok(user);
    }
}

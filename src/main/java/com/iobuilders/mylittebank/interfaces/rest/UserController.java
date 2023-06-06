package com.iobuilders.mylittebank.interfaces.rest;

import com.iobuilders.mylittebank.domain.model.BankUser;
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
    public ResponseEntity<BankUser> registerUser(@RequestBody BankUser bankUserRequest) {
        BankUser bankUser = userService.registerUser(bankUserRequest.getName(), bankUserRequest.getPhoneNumber(), bankUserRequest.getEmail());
        return ResponseEntity.ok(bankUser);
    }
}

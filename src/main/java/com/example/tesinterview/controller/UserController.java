package com.example.tesinterview.controller;

import com.example.tesinterview.Response;
import com.example.tesinterview.ValidationHelper;
import com.example.tesinterview.entity.User;
import com.example.tesinterview.payload.UserLoginRequest;
import com.example.tesinterview.payload.UserRegisterRequest;
import com.example.tesinterview.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private ValidationHelper validator;

    @PostMapping("/register")
    public ResponseEntity<Response<String>> register(@RequestBody UserRegisterRequest request) {
        validator.validate(request);

        userService.register(request);
        return ResponseEntity.ok().body(Response.<String>builder().message("OK").build());
    }

    @PostMapping("/login")
    public ResponseEntity<Response<User>> login(@RequestBody UserLoginRequest request) {
        validator.validate(request);

        User user = userService.login(request);
        return ResponseEntity.ok().body(Response.<User>builder().message(user).build());
    }
}

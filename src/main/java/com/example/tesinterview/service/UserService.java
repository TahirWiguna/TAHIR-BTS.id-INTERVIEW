package com.example.tesinterview.service;

import com.example.tesinterview.entity.User;
import com.example.tesinterview.payload.UserLoginRequest;
import com.example.tesinterview.payload.UserRegisterRequest;
import com.example.tesinterview.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User register(UserRegisterRequest request) {
        User user = new User();
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        return userRepository.save(user);
    }

    public User login(UserLoginRequest request) {
        Optional<User> checkUser = userRepository.findByUsernameAndPassword(request.getUsername(), request.getPassword());
        if (checkUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username/password is wrong.");
        }

        User user = checkUser.get();
        user.setToken(UUID.randomUUID().toString());
        user.setTokenExpiredAt(expiredToken());
        return userRepository.save(user);
    }

    private Long expiredToken() {
        int second = 1000;
        int minute = 60 * second;
        int hour = 60 * minute;
        int day = 24 * hour;
        int totalDay = 1;
        return System.currentTimeMillis() + ((long) totalDay * day);
    }
}

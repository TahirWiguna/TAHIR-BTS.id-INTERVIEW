package com.example.tesinterview.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserLoginRequest {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}

package com.example.tesinterview.payload;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ChecklistCreateRequest {
    @NotBlank
    private String name;
}

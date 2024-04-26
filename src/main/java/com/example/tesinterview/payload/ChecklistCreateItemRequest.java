package com.example.tesinterview.payload;

import lombok.Data;

@Data
public class ChecklistCreateItemRequest {
    private String name;
    private Boolean checked;
}

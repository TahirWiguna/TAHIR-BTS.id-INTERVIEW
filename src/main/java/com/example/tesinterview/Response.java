package com.example.tesinterview;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Response<T> {
    private T message;
}

package com.example.javaeeproject.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class RestException extends RuntimeException {
    private final HttpStatus status;

    public RestException(HttpStatus status) {
        this.status = status;
    }
}

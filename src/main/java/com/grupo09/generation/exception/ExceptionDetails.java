package com.grupo09.generation.exception;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;


@Data
@SuperBuilder
public class ExceptionDetails{
    private final String error;
    private final int status;
    private final String details;
    private final LocalDateTime timestamp;
}


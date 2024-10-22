package com.grupo09.generation.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends BadRequestExceptionDetails{
    private final String fields;
    private final String fieldsMessage;
}

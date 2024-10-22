package com.grupo09.generation.exception;

import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@SuperBuilder
public class EmailAlreadyExistExceptionDetails extends ExceptionDetails{
}

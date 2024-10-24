package com.grupo09.generation.handler;

import com.grupo09.generation.exception.*;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.method.MethodValidationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<BadRequestExceptionDetails> handleBadRequestException(BadRequestException ex){
        return new ResponseEntity<>(BadRequestExceptionDetails.builder().timestamp(LocalDateTime.now()).error("Bad request exception")
                                            .status(HttpStatus.BAD_REQUEST.value()).details(ex.getMessage()).build(),
                                    HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<NotFoundExceptionDetails> handleNotFoundException(NotFoundException ex){
        return new ResponseEntity<>(
                NotFoundExceptionDetails.builder().timestamp(LocalDateTime.now()).error("Not found").status(HttpStatus.NOT_FOUND.value())
                        .details(ex.getMessage()).build(),HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(EmailAlreadyExistException.class)
    public ResponseEntity<EmailAlreadyExistExceptionDetails> handleEmailAlreadyExistException(EmailAlreadyExistException ex){
        return new ResponseEntity<>(
                EmailAlreadyExistExceptionDetails.builder().timestamp(LocalDateTime.now()).error("Conflict").status(HttpStatus.CONFLICT.value())
                        .details(ex.getMessage()).build(),HttpStatus.CONFLICT);
    }

    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<UnauthorizedExceptionDetails> handleUnauthorizedException(UnauthorizedException ex){
        return new ResponseEntity<>(
                UnauthorizedExceptionDetails.builder().timestamp(LocalDateTime.now()).error("Conflict").status(HttpStatus.UNAUTHORIZED.value())
                        .details(ex.getMessage()).build(),HttpStatus.UNAUTHORIZED);
    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
        String fields = fieldErrors.stream()
                .map(FieldError::getField)
                .collect(Collectors.joining(", "));
        String fieldsMessage = fieldErrors.stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.joining(", "));

        ValidationExceptionDetails validationExceptionDetails = ValidationExceptionDetails.builder()
                .timestamp(LocalDateTime.now())
                .status(status.value())
                .details("Verifique os campos com erro")
                .fields(fields)
                .fieldsMessage(fieldsMessage)
                .build();

        return new ResponseEntity<>(validationExceptionDetails, HttpStatus.BAD_REQUEST);
    }
}


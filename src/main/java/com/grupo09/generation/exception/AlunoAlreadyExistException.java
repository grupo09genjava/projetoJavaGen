package com.grupo09.generation.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@ResponseStatus(HttpStatus.CONFLICT)
public class AlunoAlreadyExistException extends RuntimeException{
    public AlunoAlreadyExistException(String message){
        super(message);
    }
}

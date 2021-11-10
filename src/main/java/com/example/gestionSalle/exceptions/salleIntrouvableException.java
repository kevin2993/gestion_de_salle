package com.example.gestionSalle.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class salleIntrouvableException extends RuntimeException {
    private static final Long serialVersionUID=1L;

    private String message;

    public salleIntrouvableException(String message) {
        this.message = message;
    }
    public String getMessage(){
        return message;
    }

    public void setMessage(String message){
        this.message = message;
    }
}

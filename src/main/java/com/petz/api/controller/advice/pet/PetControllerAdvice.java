package com.petz.api.controller.advice.pet;

import com.petz.api.exception.pet.PetNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PetControllerAdvice {

    @ResponseBody
    @ExceptionHandler(PetNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String petNotFoundHandler(PetNotFoundException ex) {
        return ex.getMessage();
    }
}

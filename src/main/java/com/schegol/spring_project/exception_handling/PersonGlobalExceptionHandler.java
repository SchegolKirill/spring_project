package com.schegol.spring_project.exception_handling;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PersonGlobalExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<PersonIncorrectData> handleException(NoSuchPersonException exception) {
        PersonIncorrectData data = new PersonIncorrectData();
        data.setInfo(exception.getMessage());
        return new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
}

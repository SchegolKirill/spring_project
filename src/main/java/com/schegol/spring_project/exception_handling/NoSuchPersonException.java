package com.schegol.spring_project.exception_handling;

public class NoSuchPersonException extends RuntimeException{

    public NoSuchPersonException (String message){
        super(message);
    }
}

package com.icaro.capacitacao.exception;

public class UserNotFoundException extends ResourceNotFoundException{
    public UserNotFoundException(String message){
        super(message);
    }
}

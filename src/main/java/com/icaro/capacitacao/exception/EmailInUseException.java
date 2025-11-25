package com.icaro.capacitacao.exception;

public class EmailInUseException extends ResourceNotFoundException{
    public EmailInUseException(String message){
        super(message);
    }
}

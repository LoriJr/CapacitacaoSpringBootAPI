package com.icaro.capacitacao.exception;

public class OrderNotFoundException extends ResourceNotFoundException{
    public OrderNotFoundException(String message){
        super(message);
    }
}

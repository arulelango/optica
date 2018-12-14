package com.atos.exception;

public class CustomerNotFoundException extends AtosApplicationException {
    public CustomerNotFoundException(String msg){
        super(msg);
    }
}

package com.ifba.projeto.tcc.domain.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message) {
        super(message);
    }
    
    public ResourceNotFoundException(String resource, Object identifier) {
        super(String.format("%s com identificador '%s' não encontrado(a)", resource, identifier));
    }
}


package com.konex.inventario.infrastructure.adapter.in.web.exception;

public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resource, Long id) {
        super(String.format("%s con ID %d no encontrado", resource, id));
    }
}
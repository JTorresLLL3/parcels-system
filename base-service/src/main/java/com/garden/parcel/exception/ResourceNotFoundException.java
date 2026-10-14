package com.garden.parcel.exception;

/**
 * Excepción personalizada lanzada cuando un recurso solicitado no existe.
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s no encontrado(a) con %s: '%s'", resourceName, fieldName, fieldValue));
    }
}

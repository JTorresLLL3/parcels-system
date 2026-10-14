package com.garden.parcel.dto.response;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Estructura estándar y personalizada para respuestas de error de la API.
 */
public class ErrorResponseDTO {

    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;
    private List<ValidationErrorItem> errors;

    public ErrorResponseDTO() {
        this.timestamp = LocalDateTime.now();
        this.errors = new ArrayList<>();
    }

    public ErrorResponseDTO(int status, String error, String message, String path) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = new ArrayList<>();
    }

    public ErrorResponseDTO(int status, String error, String message, String path, List<ValidationErrorItem> errors) {
        this.timestamp = LocalDateTime.now();
        this.status = status;
        this.error = error;
        this.message = message;
        this.path = path;
        this.errors = errors != null ? errors : new ArrayList<>();
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<ValidationErrorItem> getErrors() {
        return errors;
    }

    public void setErrors(List<ValidationErrorItem> errors) {
        this.errors = errors;
    }
}

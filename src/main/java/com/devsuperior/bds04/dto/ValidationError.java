package com.devsuperior.bds04.dto;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError {

    public static record Entry(String fieldName, String message) {}

    private Integer status;
    private Instant timestamp;
    private String message;
    private String path;

    private final List<Entry> errors = new ArrayList<>();

    public ValidationError() {
    }

    public ValidationError(Integer status, Instant timestamp, String message, String path) {
        this.status = status;
        this.timestamp = timestamp;
        this.message = message;
        this.path = path;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
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

    public List<Entry> getErrors() {
        return errors;
    }

    public void addError(String fieldName, String message) {
        errors.add(new Entry(fieldName, message));
    }

}

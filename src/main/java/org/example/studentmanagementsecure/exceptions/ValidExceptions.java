package org.example.studentmanagementsecure.exceptions;

import java.time.LocalDateTime;
import java.util.Map;

public class ValidExceptions {
    private LocalDateTime timestamp;
    private int status;
    private String errorName;
    private String errorMessage;
    private String path;
    private Map<String,String> fieldErrors;

    public ValidExceptions(LocalDateTime timestamp, int status, String errorName, String errorMessage, String path, Map<String, String> fieldErrors) {
        this.timestamp = timestamp;
        this.status = status;
        this.errorName = errorName;
        this.errorMessage = errorMessage;
        this.path = path;
        this.fieldErrors = fieldErrors;
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

    public String getErrorName() {
        return errorName;
    }

    public void setErrorName(String errorName) {
        this.errorName = errorName;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Map<String, String> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(Map<String, String> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }
}

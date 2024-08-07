package br.edu.ifs.apinewsigaa.rest.dto;

import java.time.LocalDateTime;

public class ApiResponse<T> {
    private LocalDateTime timestamp;
    private String requestId;
    private String message;
    private T data;

    public ApiResponse(String requestId, String message, T data) {
        this.timestamp = LocalDateTime.now();
        this.requestId = requestId;
        this.message = message;
        this.data = data;
    }

    // Getters and Setters
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
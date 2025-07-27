package com.techie.microservices.order.dto;
public class ApiResponse<T> {
    private String message;
    private T data;

    public ApiResponse(String message, T data) {
        this.message = message;
        this.data = data;
    }

    // Getters
    public String getMessage() { return message; }
    public T getData() { return data; }
}

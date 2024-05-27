package com.dfanso.spring_jwt_auth.auth.dto;


public class ErrorResponseDto {

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public ErrorResponseDto(String message) {
        this.message = message;
    }
}
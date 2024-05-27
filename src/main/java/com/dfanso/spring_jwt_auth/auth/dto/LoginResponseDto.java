package com.dfanso.spring_jwt_auth.auth.dto;

public class LoginResponseDto {
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    private String message;

    public LoginResponseDto(String message) {
        this.message = message;
    }
}

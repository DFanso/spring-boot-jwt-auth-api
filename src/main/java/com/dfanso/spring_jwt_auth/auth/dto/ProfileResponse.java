package com.dfanso.spring_jwt_auth.auth.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ProfileResponse {

    private String email;
    private String firstName;
    private String lastName;

    public ProfileResponse(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

}

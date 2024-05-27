package com.dfanso.spring_jwt_auth.auth.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserDto {

    @Schema(hidden = true)
    private Long id;
    private String firstname;
    private String lastname;
    private String email;
    private String password;

}

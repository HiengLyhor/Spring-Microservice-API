package com.jwt.token.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter @Setter
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class ValidateLoginResponse {

    String token;

    int code;

    String message;

    public void successLogin(String token) {
        this.token = token;
        this.code = HttpStatus.OK.value();
        this.message = "Login successfully.";
    }

    public void failLogin(String message) {
        this.code = HttpStatus.UNAUTHORIZED.value();
        this.message = message;
    }
}

package com.jwt.token.utility.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Status {

    private Integer code;

    private String message;

    public Status(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Status(String message) {
        this.code = HttpStatus.OK.value();
        this.message = message;
    }

}

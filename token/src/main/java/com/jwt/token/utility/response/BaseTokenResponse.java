package com.jwt.token.utility.response;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@JsonNaming(PropertyNamingStrategies.UpperCamelCaseStrategy.class)
public class BaseTokenResponse {

    String token;

    Timestamp effectiveDate;

    Timestamp expireDate;

    public BaseTokenResponse(String token) {

        if (token == null) {
            this.effectiveDate = new Timestamp(System.currentTimeMillis());
            this.expireDate = new Timestamp(System.currentTimeMillis());
        } else {
            this.token = token;
            this.effectiveDate = new Timestamp(System.currentTimeMillis());
            this.expireDate = new Timestamp(System.currentTimeMillis() + 900000);
        }
    }

}

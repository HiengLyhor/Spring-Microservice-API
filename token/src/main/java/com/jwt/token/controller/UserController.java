package com.jwt.token.controller;

import com.jwt.token.dto.UserRegisterRequest;
import com.jwt.token.dto.UserRegisterResponse;
import com.jwt.token.dto.ValidateLoginResponse;
import com.jwt.token.service.UserEntityService;
import com.jwt.token.utility.config.RequestSecurity;
import com.jwt.token.utility.response.ResponseTemplate;
import com.jwt.token.utility.response.Status;
import com.jwt.token.utility.response.BaseTokenResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;

@RestController
@RequestMapping("authentication/")
public class UserController {

    @Autowired
    UserEntityService service;

    @GetMapping("/")
    String homePage(HttpServletRequest request) {
        return "Welcome to JWT Token!\n" + request.getSession().getId();
    }

    @PostMapping("register")
    ResponseTemplate<Object> registerUser(@RequestBody UserRegisterRequest req, HttpServletRequest http) {

        UserRegisterResponse registerRes = service.registerUser(req, new RequestSecurity().getClientIpAddress(http));
        Status status = new Status(registerRes.getCode(), registerRes.getMessage());

        return ResponseTemplate.builder()
                .status(status)
                .data(registerRes)
                .timestamp(new Timestamp(System.currentTimeMillis()))
                .build();

    }

    @PostMapping("login")
    ResponseTemplate<BaseTokenResponse> login(@RequestBody UserRegisterRequest req) {

        ValidateLoginResponse tokenValue = service.verify(req);
        Status status = new Status();

        status.setCode(tokenValue.getCode());
        status.setMessage(tokenValue.getMessage());

        BaseTokenResponse dataResponse = new BaseTokenResponse(tokenValue.getToken());

        ResponseTemplate<BaseTokenResponse> result = new ResponseTemplate<>();

        result.setStatus(status);
        result.setData(dataResponse);
        result.setTimestamp(new Timestamp(System.currentTimeMillis()));

        return result;

    }

}

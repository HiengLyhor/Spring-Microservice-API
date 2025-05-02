package com.jwt.token.utility.config;

import jakarta.servlet.http.HttpServletRequest;

public class RequestSecurity {

    public String getClientIpAddress(HttpServletRequest request) {

        String ipAddress = request.getHeader("X-Forwarded-For");

        if (ipAddress == null || ipAddress.isEmpty() || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
        }

        return ipAddress;
    }

}

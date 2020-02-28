package com.manas.learning.session.config;

import org.springframework.session.web.http.HttpSessionIdResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.List;

public class CustomHeaderHttpSessionIdResolver implements HttpSessionIdResolver {

    private static final String HEADER_X_AUTH_TOKEN = "X-Auth-Token";
    private static final String HEADER_AUTHENTICATION_INFO = "Authentication-Info";

    private final String headerName;

    public static CustomHeaderHttpSessionIdResolver xAuthToken() {
        return new CustomHeaderHttpSessionIdResolver("X-Auth-Token");
    }

    public static CustomHeaderHttpSessionIdResolver authenticationInfo() {
        return new CustomHeaderHttpSessionIdResolver("Authentication-Info");
    }

    public CustomHeaderHttpSessionIdResolver(String headerName) {
        if (headerName == null) {
            throw new IllegalArgumentException("headerName cannot be null");
        } else {
            this.headerName = headerName;
        }
    }

    public List<String> resolveSessionIds(HttpServletRequest request) {
        String headerValue = request.getHeader(this.headerName);
        return headerValue != null ? Collections.singletonList(headerValue) : Collections.emptyList();
    }

    public void setSessionId(HttpServletRequest request, HttpServletResponse response, String sessionId) {
        response.setHeader(this.headerName, sessionId);
    }

    public void expireSession(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader(this.headerName, "");
    }
}


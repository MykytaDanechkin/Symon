package com.mykyda.symon.security.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import java.io.IOException;

@Slf4j
public class AuthenticationEntryPoint extends LoginUrlAuthenticationEntryPoint {
    public AuthenticationEntryPoint() {
        super("");
    }

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        log.debug(authException.getMessage());
        System.out.println(authException.getMessage());
        response.sendError(404, "NotFound");
    }
}
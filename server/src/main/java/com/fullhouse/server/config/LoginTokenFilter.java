package com.fullhouse.server.config;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginTokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String path = httpRequest.getRequestURI();

        if (path.startsWith("/oauth2/authorization/")) {
            String loginToken = httpRequest.getParameter("loginToken");
            if (loginToken != null && !loginToken.isBlank()) {
                HttpSession session = httpRequest.getSession(true);
                session.setAttribute("loginToken", loginToken);
            }
        }

        chain.doFilter(request, response);
    }
}

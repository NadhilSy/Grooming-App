package com.enigma.grooming.controller.interceptor;

import com.enigma.grooming.exception.UnauthorizedException;
import com.enigma.grooming.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.Arrays;

@Component
public class MyHeaderInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (request.getRequestURI().contains("/auth/login") ||
                request.getRequestURI().contains("/auth/register")) {
            return true;
        } else {
            String uri = request.getRequestURI();
            String httpMethod = request.getMethod();

            boolean adminRoutes = (uri.matches("/transactions/(approve|finish)/\\d") && httpMethod.equals("PUT")) || (uri.matches("/packages") && httpMethod.equals("POST"));
            if (adminRoutes) {
                System.out.println(isAdmin(request));
                if (isAdmin(request)) {
                    return true;
                } else {
                    throw new UnauthorizedException("Not enough role");
                }
            }
            return validateToken(request);
        }
    }

    private boolean validateToken(HttpServletRequest request) throws UnauthorizedException {
        String token = request.getHeader("Authorization");
        if (token == null) throw new UnauthorizedException();
        return jwtUtil.validateJwtToken(token.split(" ")[1]);
    }

    private boolean AdminRoute(String route) {
        String[] admin = {"/packages", "/transactions/approve"};
        return Arrays.stream(admin).anyMatch(route::contains);
    }

    public String extractToken(HttpServletRequest request) {
        return request.getHeader("Authorization").split(" ")[1];
    }

    private boolean isAdmin(HttpServletRequest request) throws UnauthorizedException {
        boolean tokenIsValid = validateToken(request);
        if (tokenIsValid) {
            String token = extractToken(request);
            String role = jwtUtil.getRole(token);
            System.out.println(role);
            return role.equals("admin");
        }
        throw new UnauthorizedException("Not enough role");
    }
}

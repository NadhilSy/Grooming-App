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
            boolean validated = validateToken(request);
            if (AdminRoute(request.getRequestURI()) && request.getMethod().equals("POST")) {
                System.out.println("admin route");
                return isAdmin(request);
            }
            if (validated) {
                return true;
            } else {
                throw new UnauthorizedException();
            }
        }
    }

    private boolean validateToken(HttpServletRequest request) throws UnauthorizedException {
        String token = request.getHeader("Authorization");
        if (token == null) throw new UnauthorizedException();
        return jwtUtil.validateJwtToken(token.split(" ")[1]);
    }

    private boolean AdminRoute(String route) {
        String[] admin = {"/packages"};
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
            return role.equals("ADMIN");
        }
        throw new UnauthorizedException("Not enough role");
    }
}

package com.enigma.grooming.controller.interceptor;

import com.enigma.grooming.util.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class MyHeaderInterceptor implements HandlerInterceptor {
    @Autowired
    JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        if (request.getRequestURI().contains(UrlMapping.REGISTER) ||
//                request.getRequestURI().contains(UrlMapping.LOGIN)) {
//            return true;
//        }
        return true;
//        String token = request.getHeader("Authorization");
//        System.out.println("TOKEN " + token);
////        if (token == null) throw new UnauthorizedException();
//        String[] bearerToken = token.split(" ");
//        return jwtUtil.validateJwtToken(bearerToken[1]);
//        return true;
    }
}

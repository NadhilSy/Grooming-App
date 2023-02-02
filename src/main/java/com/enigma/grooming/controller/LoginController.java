package com.enigma.grooming.controller;

import com.enigma.grooming.model.request.GoogleAccountRequest;
import com.enigma.grooming.model.request.RegistrationRequest;
import com.enigma.grooming.model.response.SuccessResponse;
import com.enigma.grooming.service.AuthService;
import com.enigma.grooming.service.SystemAuthService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class LoginController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    AuthService authService;

    @Autowired
    SystemAuthService systemAuthService;
//    @PostMapping
//    public ResponseEntity currentUser(OAuth2AuthenticationToken oAuth2AuthenticationToken){
//        Map<String ,Object> data = oAuth2AuthenticationToken.getPrincipal().getAttributes();
//        GoogleAccountRequest google = modelMapper.map(data,GoogleAccountRequest.class);
//        String token = authService.login(google);
//
////        System.out.println(google);
//        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Login",token));
//    }

    @Order(2)
    @Transactional
    @PostMapping("/login2")
    public ResponseEntity currentUser2(RegistrationRequest registrationRequest){
//        Map<String ,Object> data = oAuth2AuthenticationToken.getPrincipal().getAttributes();
//        GoogleAccountRequest google = modelMapper.map(data,GoogleAccountRequest.class);
        String token = systemAuthService.login(registrationRequest);

//        System.out.println(google);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success Login",token));
    }

    @Order(1)
    @Transactional
    @PostMapping("/register")
    public ResponseEntity register(@RequestBody RegistrationRequest registrationRequest) {
        String token = systemAuthService.register(registrationRequest);
        return ResponseEntity.status(HttpStatus.OK).body(new SuccessResponse<>("Success registration", token));
    }
}

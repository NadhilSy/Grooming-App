package com.enigma.grooming.service;

import com.enigma.grooming.model.request.GoogleAccountRequest;
import com.enigma.grooming.model.request.RegistrationGoogleRequest;
//import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;

public interface AuthService {
    String register(GoogleAccountRequest googleAccountRequest);
    String login(GoogleAccountRequest googleAccountRequest);
}

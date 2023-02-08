package com.enigma.grooming.service;

import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.LoginRequest;
import com.enigma.grooming.model.request.RegistrationRequest;

public interface SystemAuthService {
    User register(RegistrationRequest registrationRequest);
    String login(LoginRequest loginRequest);
}

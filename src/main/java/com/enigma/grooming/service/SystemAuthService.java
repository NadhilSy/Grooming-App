package com.enigma.grooming.service;

import com.enigma.grooming.model.request.GoogleAccountRequest;
import com.enigma.grooming.model.request.RegistrationRequest;

public interface SystemAuthService {
    String register(RegistrationRequest registrationRequest);
    String login(RegistrationRequest registrationRequest);
}

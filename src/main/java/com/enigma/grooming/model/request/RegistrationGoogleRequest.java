package com.enigma.grooming.model.request;

import com.enigma.grooming.util.Role;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegistrationGoogleRequest {
    private String name;
    private String address;
    private String phoneNumber;
    private String email;
    private String googleId;
//    private String password;
    private String photo;
    private Role role;
    private boolean isActive;
}

package com.enigma.grooming.model.request;

import com.enigma.grooming.util.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@ToString
public class RegistrationRequest {
    @Length(min = 3)
    private String name;
    @NotBlank
    private String address;
    private String phoneNumber;
    @Email(message = "email not valid")
    private String email;
//    private String googleId;
    private String password;
    private String photo;
    private Role role;
//    private boolean isActive;
}

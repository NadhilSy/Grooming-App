package com.enigma.grooming.model.request;

import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@ToString
public class LoginRequest {
    @Email(message = "email not valid")
    private String email;
    @Length(min = 6, message = "password is too short")
    private String password;
}

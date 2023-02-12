package com.enigma.grooming.model.response;

import com.enigma.grooming.util.Role;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEncapsulated {
    private Integer userId;
    private String userName;
    private String address;
    private String phoneNumber;
    private String role;

    public UserEncapsulated(Integer userId, String userName, String address, String phoneNumber, Role role) {
        this.userId = userId;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.userName = userName;
        this.role = role.toString();
    }
}

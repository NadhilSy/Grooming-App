package com.enigma.grooming.model.request;

import com.enigma.grooming.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
public class CatRequest {
    private String token;
    private String catName;
    private String color;
    private String gender;
    private String race;
    private String catImageUrl;
//    private User user;

   //user
    private String name;
    private String address;
    private String phoneNumber;
    private String photo;

}

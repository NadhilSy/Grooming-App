package com.enigma.grooming.model.request;

import com.enigma.grooming.model.User;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter
public class CatRequest {
    private User userInfo;
    private String catName;
    private String color;
    private String gender;
    private String race;
    private String catImageUrl;
}

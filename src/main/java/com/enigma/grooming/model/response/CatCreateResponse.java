package com.enigma.grooming.model.response;

import com.enigma.grooming.model.Cat;
import com.enigma.grooming.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class CatCreateResponse {
    private String catName;
    private String catColor;
    private String gender;
    private String race;
    private String catImage;

    @Getter
    @Setter
    private class Owner {
        private String name;
        private String address;
        private String phone;
    }

    Owner owner;

    public CatCreateResponse(Cat cat, User user) {
        setCatName(cat.getCatName());
        setCatColor(cat.getColor());
        setGender(cat.getGender());
        setRace(cat.getRace());
        setCatImage(cat.getCatImageUrl());
        this.owner = new Owner();
        this.owner.setName(user.getName());
        this.owner.setAddress(user.getAddress());
        this.owner.setPhone(user.getPhoneNumber());
    }
}

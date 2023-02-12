package com.enigma.grooming.model.response;

import com.enigma.grooming.model.Cat;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CatEncapsulated {
    private String id;
    private String name;
    private String color;
    private String gender;
    private String race;
    private String catImage;
    public CatEncapsulated(Cat cat) {
        setId(cat.getCatId());
        setName(cat.getCatName());
        setColor(cat.getColor());
        setGender(cat.getGender());
        setRace(cat.getRace());
        setCatImage(cat.getCatImageUrl());
    }
}

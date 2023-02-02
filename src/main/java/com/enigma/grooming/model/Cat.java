package com.enigma.grooming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table
public class Cat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Integer catId;

    @Column(name = "cat_name")
    private String catName;

    @Column(name = "color")
    private String color;

    @Column(name = "gender")
    private String gender;

    @Column(name = "race")
    private String race;

    @Column(name = "cat_image_url")
    private String catImageUrl;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "user_id", nullable = false)
    @ToString.Exclude
    private User user;
}

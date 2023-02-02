package com.enigma.grooming.model;

import com.enigma.grooming.util.Role;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table
@Setter
@Getter
@ToString
public class Auth {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "auth_id")
//    private Integer authId;

    @Id
    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @Column(name = "google_id", nullable = false)
    private String googleId;

    @Column(name = "is_active", columnDefinition = "boolean default false")
    private boolean isActive;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

}


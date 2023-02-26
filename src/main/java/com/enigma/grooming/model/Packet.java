package com.enigma.grooming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;
import org.hibernate.annotations.SQLDelete;

@Getter
@Setter
@ToString
@Entity
@Table(name = "packet")
public class Packet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "package_id")
    private Integer packageId;

    @Column(name = "package_name", unique = true)
    private String packageName;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Long price;
    @Column(name = "is_deleted")
    private Boolean isDeleted = false;
}

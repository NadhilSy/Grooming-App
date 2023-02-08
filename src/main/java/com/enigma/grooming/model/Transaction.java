package com.enigma.grooming.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Entity
@Table(name = "transaction")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Integer transactionId;

    @ManyToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", nullable = false)
    private Packet aPacket;

    @ManyToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id", nullable = false)
    private Cat cat;

    @ManyToOne
    @JoinColumn(name = "status_id", referencedColumnName = "status_id", nullable = false)
    private Status status;


}

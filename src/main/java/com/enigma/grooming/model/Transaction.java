package com.enigma.grooming.model;

import com.enigma.grooming.model.constant.TrxStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

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
    @OneToOne
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", nullable = false)
    private Packet packet;
    @OneToOne
    private User user;
    @OneToOne
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id", nullable = false)
    private Cat cat;
    @Enumerated(EnumType.STRING)
    TrxStatus status;
    @Temporal(TemporalType.DATE)
    LocalDate dateIssued;
}

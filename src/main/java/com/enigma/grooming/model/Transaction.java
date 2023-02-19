package com.enigma.grooming.model;

import com.enigma.grooming.model.constant.TrxStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

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
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "package_id", referencedColumnName = "package_id", nullable = false)
    private Packet packet;
    @Column(name = "total")
    Long total;
    @ManyToOne
    @JoinColumn(name = "customer")
    private User customer;
    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "cat_id", referencedColumnName = "cat_id", nullable = false)
    private Cat cat;
    @Enumerated(EnumType.STRING)
    TrxStatus status;
    @Temporal(TemporalType.DATE)
    @Column(name = "date_created")
    LocalDate dateCreated;
}

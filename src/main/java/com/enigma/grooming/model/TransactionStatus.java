package com.enigma.grooming.model;

import com.enigma.grooming.model.constant.TrxStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "transaction_status")
@Getter
@Setter
public class TransactionStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @Enumerated(value = EnumType.STRING)
    TrxStatus status;
}

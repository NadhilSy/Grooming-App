package com.enigma.grooming.repository;

import com.enigma.grooming.model.TransactionStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStatusRepository extends JpaRepository<TransactionStatus,Integer> {
}

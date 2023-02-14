package com.enigma.grooming.repository;

import com.enigma.grooming.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    @Query(value = "SELECT SUM(total) FROM transaction WHERE EXTRACT(MONTH FROM date_created) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM date_created) = EXTRACT(YEAR FROM CURRENT_DATE) AND status = 'PAID'", nativeQuery = true)
    Long getThisMonthTotal();

}
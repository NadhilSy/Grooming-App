package com.enigma.grooming.repository;

import com.enigma.grooming.model.TopSpender;
import com.enigma.grooming.model.Transaction;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.constant.TrxStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    List<Transaction> getAllByStatus(TrxStatus trxStatus);

    List<Transaction> findAllByCustomer(User user);

    @Query(value = "SELECT count(transaction_id) FROM transaction WHERE EXTRACT(MONTH FROM date_created) = EXTRACT(MONTH FROM CURRENT_DATE) AND EXTRACT(YEAR FROM date_created) = EXTRACT(YEAR FROM CURRENT_DATE) AND status = 'PAID'", nativeQuery = true)
    Long getThisMonthSum();

    @Query(value = "SELECT SUM(total) FROM transaction where status='PAID'", nativeQuery = true)
    Long getOmzet();

    @Query(value = "SELECT SUM(total) FROM transaction WHERE EXTRACT(MONTH FROM date_created) = EXTRACT(MONTH FROM  CURRENT_DATE) AND status = 'PAID'", nativeQuery = true)
    Long getThisMonthTotal();

    @Query(value = "SELECT cus.user_id,cus.name,SUM(total) total_spent FROM transaction t JOIN user_account cus ON t.customer = cus.user_id where t.status = 'PAID' GROUP BY cus.user_id,cus.name ORDER BY total_spent DESC LIMIT 3", nativeQuery = true)
    List<TopSpender> getTopSpender();
}
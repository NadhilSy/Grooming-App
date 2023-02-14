package com.enigma.grooming.service;

import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.model.Summary;
import com.enigma.grooming.model.Transaction;
import com.enigma.grooming.model.request.TransactionRequest;
import org.springframework.data.domain.Page;

public interface TransactionService {
    Page<Transaction> getAll(Integer page, Integer size, String direction, String sortBy);
    Transaction getById(Integer id) throws NotFoundException;
    Transaction make(TransactionRequest request);
    Transaction approve(Integer id) throws NotFoundException;
    Transaction setStatus(String status, Integer id);
    String finish(Integer id);
    Summary getTotal();
}

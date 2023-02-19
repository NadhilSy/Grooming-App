package com.enigma.grooming.service;

import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.model.Summary;
import com.enigma.grooming.model.Transaction;
import com.enigma.grooming.model.request.TransactionRequest;
import com.enigma.grooming.model.response.EncapsulateTransaction;
import com.enigma.grooming.model.response.TransactionResponse;
import com.enigma.grooming.model.response.UserEncapsulated;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TransactionService {
    Page<Transaction> getAll(Integer page, Integer size, String direction, String sortBy);

    Transaction getById(Integer id) throws NotFoundException;

    Transaction make(TransactionRequest request);

    Transaction approve(Integer id) throws NotFoundException;

    Transaction setStatus(String status, Integer id);

    String finish(Integer id);

    List<EncapsulateTransaction> getAllByStatus(String status);

    Summary getTotal();
}

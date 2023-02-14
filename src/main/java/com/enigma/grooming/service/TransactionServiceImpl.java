package com.enigma.grooming.service;

import com.enigma.grooming.exception.NeedApprovalException;
import com.enigma.grooming.exception.NotFoundException;
import com.enigma.grooming.model.*;
import com.enigma.grooming.model.constant.TrxStatus;
import com.enigma.grooming.model.request.TransactionRequest;
import com.enigma.grooming.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
@Transactional
public class TransactionServiceImpl implements TransactionService {
    TransactionRepository transactionRepository;
    ModelMapper modelMapper;
    CatService catService;
    PacketService packetService;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, ModelMapper modelMapper, CatService catService, PacketService packetService) {
        this.transactionRepository = transactionRepository;
        this.modelMapper = modelMapper;
        this.catService = catService;
        this.packetService = packetService;
    }

    @Override
    public Page<Transaction> getAll(Integer page, Integer size, String direction, String sortBy) {
        Sort sort = Sort.by(Sort.Direction.valueOf(direction), sortBy);
        Pageable pageable = PageRequest.of((page - 1), size, sort);
        return transactionRepository.findAll(pageable);
    }

    @Override
    public Transaction make(TransactionRequest request) {
        User user = modelMapper.map(request.getUser(), User.class);
        Packet packet = packetService.get(request.getPacketId());
        Cat cat = catService.get(request.getCatId());
        Transaction trx = new Transaction();
        trx.setPacket(packet);
        trx.setStatus(TrxStatus.PENDING);
        trx.setCustomer(user);
        trx.setCat(cat);
        trx.setTotal(packet.getPrice());
        trx.setDateCreated(LocalDate.now());
        return transactionRepository.save(trx);
    }

    @Override
    public Transaction getById(Integer id) throws NotFoundException {
        Optional<Transaction> trx = transactionRepository.findById(id);
        if (trx.isEmpty()) {
            throw new NotFoundException("transaction with id " + id + " was not found");
        } else {
            return trx.get();
        }
    }

    @Override
    public Transaction approve(Integer id) throws NotFoundException {
        Transaction trx = getById(id);
        trx.setStatus(TrxStatus.ON_GOING);
        return trx;
    }

    @Override
    public Transaction setStatus(String status, Integer id) {
        Transaction trx = getById(id);
        TrxStatus trxStatus = TrxStatus.valueOf(status);
        trx.setStatus(trxStatus);
        return trx;
    }

    @Override
    public String finish(Integer id) {
        Transaction trx = getById(id);
        if (trx.getStatus().toString().equals("PENDING")) {
            throw new NeedApprovalException();
        }
        trx.setStatus(TrxStatus.PAID);
        return "Transactions success with id:" + trx.getTransactionId();
    }

    @Override
    public Summary getTotal() {
        Long thisMonthSale = transactionRepository.getThisMonthTotal();
        Long omzet = transactionRepository.getOmzet();
        Long thisMonthSum = transactionRepository.getThisMonthSum();
        return new Summary(thisMonthSale, thisMonthSum, omzet,transactionRepository.getTopSpender());
    }
}

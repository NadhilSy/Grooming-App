package com.enigma.grooming.controller;

import com.enigma.grooming.model.SystemAuth;
import com.enigma.grooming.model.Transaction;
import com.enigma.grooming.model.User;
import com.enigma.grooming.model.request.TransactionRequest;
import com.enigma.grooming.model.response.CommonResponse;
import com.enigma.grooming.model.response.PagingResponse;
import com.enigma.grooming.model.response.SuccessResponse;
import com.enigma.grooming.model.response.TransactionResponse;
import com.enigma.grooming.service.SystemAuthService;
import com.enigma.grooming.service.TransactionService;
import com.enigma.grooming.service.UserService;
import com.enigma.grooming.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    @Autowired
    SystemAuthService systemAuthService;
    @Autowired
    UserService userService;
    @Autowired
    TransactionService transactionService;
    @Autowired
    JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<CommonResponse> getAllTrx(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "5") Integer size,
            @RequestParam(defaultValue = "DESC") String direction,
            @RequestParam(defaultValue = "transactionId") String sortBy
    ) {
        Page<Transaction> transactions = transactionService.getAll(page, size, direction, sortBy);
        return ResponseEntity.status(HttpStatus.OK).body(new PagingResponse<>("Success get cats", transactions));
    }

    @GetMapping("{id}")
    public ResponseEntity<CommonResponse> getById(@PathVariable(name = "id") Integer id) {
        Transaction trx = transactionService.getById(id);
        TransactionResponse transactionResponse = new TransactionResponse(trx);
        return ResponseEntity.ok(transactionResponse);
    }

    @PostMapping
    public ResponseEntity<CommonResponse> make(@RequestBody TransactionRequest request, @RequestHeader(name = "Authorization") String authorization) {
        String token = authorization.split(" ")[1];
        String email = jwtUtil.getMail(token);
        SystemAuth systemAuth = systemAuthService.findByEmail(email);
        User user = userService.findBySystemAuth(systemAuth).get();
        request.setUser(user);
        Transaction trx = transactionService.make(request);
        TransactionResponse response = new TransactionResponse(trx);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<CommonResponse> approve(@PathVariable(name = "id") Integer id) {
        Transaction trx = transactionService.approve(id);
        SuccessResponse<TransactionResponse> response = new SuccessResponse<TransactionResponse>("Success approve transactions", new TransactionResponse(trx));
        return ResponseEntity.ok(response);
    }

    @PutMapping("/finish/{id}")
    public ResponseEntity<CommonResponse> finishTransaction(@PathVariable(name = "id") Integer id) {
        String response = transactionService.finish(id);
        SuccessResponse<String> resp = new SuccessResponse<>(response, "Ok");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/summary")
    public ResponseEntity<CommonResponse> getSummary() {
        SuccessResponse<Long> response = new SuccessResponse<>("success get data", transactionService.getTotal());
        return ResponseEntity.ok(response);
    }
}

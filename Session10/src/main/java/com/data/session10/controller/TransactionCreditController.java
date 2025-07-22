package com.data.session10.controller;

import com.data.session10.model.entity.TransactionCredit;
import com.data.session10.service.TransactionCreditService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/credit-transactions")
@RequiredArgsConstructor
public class TransactionCreditController {

    private final TransactionCreditService transactionCreditService;

    @PostMapping
    public ResponseEntity<TransactionCredit> create(@Validated @RequestBody TransactionCredit transactionCredit) {
        TransactionCredit created = transactionCreditService.createTransaction(transactionCredit);
        return new ResponseEntity<>(created, HttpStatus.CREATED);
    }
}
package com.data.session10.controller;


import com.data.session10.model.entity.CreditCard;
import com.data.session10.service.CreditCardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/creditcards")
@RequiredArgsConstructor
public class CreditCardController {

    private final CreditCardService creditCardService;

    @PostMapping
    public ResponseEntity<CreditCard> create(@RequestBody CreditCard creditCard) {
        CreditCard created = creditCardService.create(creditCard);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditCard> updateLimit(@PathVariable Long id,
                                                  @RequestBody CreditCard creditCard) {
        CreditCard updated = creditCardService.updateLimit(id, creditCard);
        return ResponseEntity.ok(updated);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<CreditCard> updateStatus(@PathVariable Long id,
                                                   @RequestBody CreditCard creditCard) {
        CreditCard updated = creditCardService.updateStatus(id, creditCard);
        return ResponseEntity.ok(updated);
    }
}

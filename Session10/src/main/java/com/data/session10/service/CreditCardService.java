package com.data.session10.service;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.CreditCard;
import com.data.session10.repository.CreditCardRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CreditCardService {

    private final CreditCardRepo creditCardRepo;
    private final AccountService accountRepo;

    @Transactional
    public CreditCard create(CreditCard creditCard) {
        Account account = accountRepo.getAccountById(creditCard.getId())
                .orElseThrow(() -> new EntityNotFoundException("Account not found"));

        if (creditCardRepo.existsByAccount(account)) {
            throw new IllegalArgumentException("This account already has a credit card.");
        }

        CreditCard card = CreditCard.builder()
                .account(account)
                .spendingLimit(creditCard.getSpendingLimit())
                .amountSpent(0.0)
                .status(CreditCard.CreditCardStatus.ACTIVE)
                .build();

        return creditCardRepo.save(card);
    }

    @Transactional
    public CreditCard updateLimit(Long id, CreditCard creditCard) {
        CreditCard card = creditCardRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found"));

        card.setSpendingLimit(creditCard.getSpendingLimit());
        return creditCardRepo.save(card);
    }

    @Transactional
    public CreditCard updateStatus(Long id, CreditCard creditCard) {
        if (creditCard.getStatus() == null) {
            throw new IllegalArgumentException("Status must not be null");
        }

        CreditCard card = creditCardRepo.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Credit card not found"));

        card.setStatus(creditCard.getStatus());
        return creditCardRepo.save(card);
    }
}

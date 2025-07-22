package com.data.session10.repository;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.CreditCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditCardRepo extends JpaRepository<CreditCard, Long> {
    Optional<CreditCard> findByAccount(Account account);
    boolean existsByAccount(Account account);
}

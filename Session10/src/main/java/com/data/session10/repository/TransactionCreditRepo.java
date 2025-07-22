package com.data.session10.repository;

import com.data.session10.model.entity.TransactionCredit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TransactionCreditRepo extends JpaRepository<TransactionCredit, UUID>{
}

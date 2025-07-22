package com.data.session10.repository;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    @Query("SELECT a FROM Account a WHERE a.cccd = :cccd")
    Account findAccountByCccd(String cccd);

    @Query("SELECT t FROM Account t WHERE t.email = :email")
    List<Transaction> findByEmail(String email);

    @Query("SELECT a FROM Account a WHERE a.id = :id")
    Account findAccountById(Long id);
}

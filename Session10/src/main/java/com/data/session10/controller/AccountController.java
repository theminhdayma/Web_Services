package com.data.session10.controller;

import com.data.session10.exception.DataBadRequestException;
import com.data.session10.exception.ResourceNotFoundException;
import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.Account.Status;
import com.data.session10.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
 
@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);

    @PostMapping
    public ResponseEntity<Account> create(@RequestBody Account account) {
        if (account == null) throw new DataBadRequestException("Dữ liệu tài khoản không hợp lệ");
        account.setStatus(Status.ACTIVE);
        Account saved = accountService.saveAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Account> update(@PathVariable Long id, @RequestBody Account newAccount) {
        Account oldAccount = accountService.getAccountById(id);
        if (oldAccount == null) throw new ResourceNotFoundException("Tài khoản không tồn tại với ID: " + id);

        log.info("🔁 Cập nhật Account - Trước:\n{}", oldAccount);

        oldAccount.setFullName(newAccount.getFullName());
        oldAccount.setEmail(newAccount.getEmail());
        oldAccount.setPhone(newAccount.getPhone());
        oldAccount.setCccd(newAccount.getCccd());
        oldAccount.setMoney(newAccount.getMoney());
        oldAccount.setStatus(newAccount.getStatus());

        Account updated = accountService.updateAccount(oldAccount);

        log.info("✅ Sau cập nhật:\n{}", updated);

        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Account acc = accountService.getAccountById(id);
        if (acc == null) throw new ResourceNotFoundException("Không tìm thấy tài khoản để xóa");
        accountService.deleteAccount(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @GetMapping
    public ResponseEntity<List<Account>> getAll() {
        return ResponseEntity.ok(accountService.findAll());
    }

    @GetMapping("/cccd/{cccd}")
    public ResponseEntity<Account> findByCccd(@PathVariable String cccd) {
        Account acc = accountService.findAccountByCccd(cccd);
        if (acc == null) throw new ResourceNotFoundException("Không tìm thấy tài khoản có CCCD: " + cccd);
        return ResponseEntity.ok(acc);
    }
}

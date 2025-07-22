package com.data.session10.service;

import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.Notification;
import com.data.session10.model.entity.Transaction;
import com.data.session10.repository.AccountRepository;
import com.data.session10.repository.NotificationRepository;
import com.data.session10.repository.TransactionRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final NotificationRepository notificationRepository;

    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Transactional
    public Transaction transferMoney(String senderEmail, String receiverEmail, Double amount, String note) {
        Transaction transaction = new Transaction();
        transaction.setSender(senderEmail);
        transaction.setReceiver(receiverEmail);
        transaction.setMoney(amount);
        transaction.setNote(note);
        transaction.setCreateAt(LocalDateTime.now().format(formatter));

        try {
            Account sender = accountRepository
                    .findByEmail(senderEmail)
                    .orElseThrow(() -> new RuntimeException("Account sender not found"));
            Account receiver = accountRepository.findByEmail(receiverEmail)
                    .orElseThrow(() -> new RuntimeException("Account receiver not found"));

            if (sender.getMoney() < amount) {
                transaction.setStatus(Transaction.Status.FAILED);
                transactionRepository.save(transaction);
                throw new RuntimeException("Insufficient balance");
            }

            // Cập nhật số dư
            sender.setMoney(sender.getMoney() - amount);
            receiver.setMoney(receiver.getMoney() + amount);
            accountRepository.save(sender);
            accountRepository.save(receiver);

            // Ghi nhận giao dịch thành công
            transaction.setStatus(Transaction.Status.COMPLETED);
            transactionRepository.save(transaction);

            // Gửi thông báo cho cả 2 bên
            sendNotification(sender, "Đã trừ " + amount + " VNĐ. Số dư hiện tại: " + sender.getMoney());
            sendNotification(receiver, "Đã cộng " + amount + " VNĐ. Số dư hiện tại: " + receiver.getMoney());

            return transaction;

        } catch (Exception ex) {
            transaction.setStatus(Transaction.Status.FAILED);
            transactionRepository.save(transaction);
            throw new RuntimeException("Transaction failed: " + ex.getMessage());
        }
    }

    private void sendNotification(Account account, String content) {
        Notification notification = Notification.builder()
                .account(account)
                .content(content)
                .status(Notification.Status.UNREAD)
                .createAt(LocalDateTime.now().format(formatter))
                .build();
        notificationRepository.save(notification);
    }

    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Transaction not found with ID: " + id));
    }

    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id)) {
            throw new RuntimeException("Transaction not found with ID: " + id);
        }
        transactionRepository.deleteById(id);
    }


    public Transaction updateTransaction(Transaction transaction) {
        if (!transactionRepository.existsById(transaction.getId())) {
            throw new RuntimeException("Transaction not found with ID: " + transaction.getId());
        }
        return transactionRepository.save(transaction);
    }

    public Transaction createTransaction(Transaction transaction) {
        transaction.setCreateAt(LocalDateTime.now().format(formatter));
        return transactionRepository.save(transaction);
    }
}

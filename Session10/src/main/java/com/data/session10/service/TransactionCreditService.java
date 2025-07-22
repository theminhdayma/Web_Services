package com.data.session10.service;


import com.data.session10.model.entity.Account;
import com.data.session10.model.entity.CreditCard;
import com.data.session10.model.entity.TransactionCredit;
import com.data.session10.repository.CreditCardRepo;
import com.data.session10.repository.TransactionCreditRepo;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TransactionCreditService{

    private final TransactionCreditRepo transactionCreditRepo;
    private final CreditCardRepo creditCardRepo;
    private final AccountService accountRepo;
    private final NotificationService notificationService;

    @Transactional
    public TransactionCreditResponseDTO spendWithCreditCard(TransactionCredit request) {
        CreditCard card = creditCardRepo.findById(request.getCreditCardSender().getId()).orElseThrow(EntityNotFoundException::new);

        Account receiver = accountRepo.getAccountById(request.getCreditCardSender().getId());

        TransactionCredit tx = TransactionCredit.builder()
                .accountReceiver(receiver)
                .creditCardSender(card)
                .note(request.getNote())
                .money(request.getMoney())
                .status(TransactionCredit.TransactionStatus.COMPLETED)
                .build();

        if (card.getAmountSpent() + request.getMoney() > card.getSpendingLimit()) {
            tx.setStatus(TransactionCredit.TransactionStatus.FAILED);
            transactionCreditRepo.save(tx);
            log.warn("Giao dịch thất bại: Vượt quá hạn mức thẻ.");
            return toResponseDTO(tx);
        }

        card.setAmountSpent(card.getAmountSpent() + request.getMoney());
        creditCardRepo.save(card);

        tx.setStatus(TransactionCredit.TransactionStatus.COMPLETED);
        transactionCreditRepo.save(tx);

        log.info("Giao dịch thành công: Thẻ {} -> Tài khoản {} | Số tiền: {}",
                card.getId(), receiver.getId(), request.getMoney());

        return toResponseDTO(tx);
    }

    @Scheduled(cron = "0 0 12 20 * ?")
    @Transactional
    public void generateMonthlySpendingReport() {
        YearMonth currentMonth = YearMonth.now();
        LocalDate start = currentMonth.atDay(1);
        LocalDate end = currentMonth.atEndOfMonth();

        List<TransactionCredit> monthlyTransactions =
                transactionCreditRepo.findAllByCreatedAtBetweenAndStatus(
                        start.atStartOfDay(), end.atTime(LocalTime.MAX), TransactionCredit.TransactionStatus.COMPLETED
                );

        Map<Long, List<TransactionCredit>> groupedByCard = monthlyTransactions.stream()
                .collect(Collectors.groupingBy(tx -> tx.getCreditCardSender().getId()));

        for (var entry : groupedByCard.entrySet()) {
            Long creditCardId = entry.getKey();
            List<TransactionCredit> txs = entry.getValue();

            double total = txs.stream().mapToDouble(TransactionCredit::getMoney).sum();
            CreditCard card = creditCardRepo.findById(creditCardId).orElse(null);

            if (card == null || card.getAccount() == null || card.getAccount().getEmail() == null) continue;

            String email = card.getAccount().getEmail();
            String fullName = card.getAccount().getFullName();

            notificationService.sendNotification(card.getAccount(), "Tổng chi tiêu tháng " +
                    currentMonth + " là " + total + " VNĐ");

            StringBuilder report = new StringBuilder();
            report.append("Xin chào ").append(fullName).append(",\n\n");
            report.append("Đây là báo cáo chi tiêu bằng thẻ tín dụng tháng ").append(currentMonth).append(":\n\n");

            for (TransactionCredit tx : txs) {
                report.append("- Ngày: ").append(tx.getCreditCardSender()).append("\n");
                report.append("  Số tiền: ").append(tx.getMoney()).append(" VNĐ\n");
                report.append("  Ghi chú: ").append(tx.getNote()).append("\n\n");
            }

            report.append("Tổng chi tiêu: ").append(total).append(" VNĐ\n");
            report.append("Trân trọng.");

        }

        log.info("Đã gửi báo cáo chi tiêu tháng " + currentMonth + " cho " + groupedByCard.size() + " khách hàng.");
    }

    private TransactionCreditResponseDTO toResponseDTO(TransactionCredit tx) {
        return TransactionCredit.builder()
                .id(tx.getId())
                .creditCardSender(tx.getCreditCardSender())
                .accountReceiver(tx.getAccountReceiver().getId())
                .money(tx.getMoney())
                .note(tx.getNote())
                .status(tx.getStatus())
                .build();
    }
}

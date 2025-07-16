package com.data.session07.service;

import com.data.session07.repository.HarvestRepository;
import com.data.session07.repository.PaymentSlipRepository;
import com.data.session07.repository.SeedRepository;
import com.data.session07.repository.WorkerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class StatisticalService {

    private final SeedRepository seedRepository;
    private final HarvestRepository harvestRepository;
    private final PaymentSlipRepository paymentSlipRepository;
    private final WorkerRepository workerRepository;

    public Integer countRemainingSeeds() {
        return seedRepository.sumAvailableQuantity();
    }

    public double totalHarvestMoneyThisMonth() {
        LocalDate now = LocalDate.now();
        return harvestRepository.sumPriceByMonth(now.getMonthValue(), now.getYear());
    }

    public double totalPaymentSlipsThisMonth() {
        LocalDate now = LocalDate.now();
        return paymentSlipRepository.countPaymentSlipsByMonth(now.getMonthValue(), now.getYear());
    }

    public Map<String, Double> profitLossOverYear() {
        int year = LocalDate.now().getYear();
        Map<String, Double> monthlyProfit = new HashMap<>();
        for (int month = 1; month <= 12; month++) {
            double revenue = harvestRepository.sumPriceByMonth(month, year);
            double expense = paymentSlipRepository.sumExpenseByMonth(month, year);
            monthlyProfit.put(String.format("%02d/%d", month, year), revenue - expense);
        }
        return monthlyProfit;
    }

    public double workerSalaryThisMonth() {
        LocalDate now = LocalDate.now();
        return workerRepository.sumSalaryByMonth(now.getMonthValue(), now.getYear());
    }
}

package com.data.session07.controller;

import com.data.session07.service.StatisticalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/statistics")
@RequiredArgsConstructor
public class StatisticalController {

    private final StatisticalService statisticalService;

    @GetMapping("/remaining-seeds")
    public int getRemainingSeeds() {
        return statisticalService.countRemainingSeeds();
    }

    @GetMapping("/harvest-money")
    public double getTotalHarvestMoneyThisMonth() {
        return statisticalService.totalHarvestMoneyThisMonth();
    }

    @GetMapping("/payment-slips")
    public double getTotalPaymentSlipsThisMonth() {
        return statisticalService.totalPaymentSlipsThisMonth();
    }

    @GetMapping("/profit-loss")
    public Map<String, Double> getProfitLossOverYear() {
        return statisticalService.profitLossOverYear();
    }

    @GetMapping("/worker-salary")
    public double getWorkerSalaryThisMonth() {
        return statisticalService.workerSalaryThisMonth();
    }
}


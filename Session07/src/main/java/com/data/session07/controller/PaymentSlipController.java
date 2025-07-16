package com.data.session07.controller;

import com.data.session07.entity.PaymentSlip;
import com.data.session07.repository.PaymentSlipRepository;
import com.data.session07.service.PaymentSlipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/payment-slips")
public class PaymentSlipController {

    private final PaymentSlipService paymentSlipService;

    @GetMapping
    public List<PaymentSlip> getPaymentSlips() {
        return paymentSlipService.findAll();
    }

    @GetMapping("/{id}")
    public PaymentSlip getPaymentSlipById(@PathVariable Long id) {
        PaymentSlip paymentSlip = paymentSlipService.findById(id);
        if (paymentSlip == null) {
            throw new RuntimeException("Payment Slip not found with id: " + id);
        }
        return paymentSlip;
    }

    @PostMapping
    public PaymentSlip createPaymentSlip(@RequestBody PaymentSlip paymentSlip) {
        if (paymentSlip == null) {
            throw new RuntimeException("Payment Slip cannot be null");
        }
        return paymentSlipService.save(paymentSlip);
    }

    @PutMapping("/{id}")
    public PaymentSlip updatePaymentSlip(@PathVariable Long id, @RequestBody PaymentSlip paymentSlip) {
        PaymentSlip existingPaymentSlip = paymentSlipService.findById(id);
        if (existingPaymentSlip == null) {
            throw new RuntimeException("Payment Slip not found with id: " + id);
        }
        paymentSlip.setId(id);
        return paymentSlipService.updateById(paymentSlip);
    }

    @DeleteMapping("/{id}")
    public void deletePaymentSlip(@PathVariable Long id) {
        PaymentSlip existingPaymentSlip = paymentSlipService.findById(id);
        if (existingPaymentSlip == null) {
            throw new RuntimeException("Payment Slip not found with id: " + id);
        }
        paymentSlipService.deleteById(id);
    }
}

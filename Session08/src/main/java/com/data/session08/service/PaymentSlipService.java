package com.data.session08.service;

import com.data.session08.entity.PaymentSlip;
import com.data.session08.exception.NoResourceFoundException;
import com.data.session08.repository.PaymentSlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentSlipService {

    @Autowired
    private PaymentSlipRepository paymentSlipRepository;

    public PaymentSlip addPaymentSlip(PaymentSlip paymentSlip) {
        paymentSlip.setCreatedAt(LocalDateTime.now());
        return paymentSlipRepository.save(paymentSlip);
    }

    public PaymentSlip updatePaymentSlip(Long id, PaymentSlip paymentSlipDetails) {
        PaymentSlip paymentSlip = paymentSlipRepository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException("Payment slip not found"));
        paymentSlip.setTitle(paymentSlipDetails.getTitle());
        paymentSlip.setDescription(paymentSlipDetails.getDescription());
        paymentSlip.setMoney(paymentSlipDetails.getMoney());
        return paymentSlipRepository.save(paymentSlip);
    }

    public void deletePaymentSlip(Long id) {
        if (!paymentSlipRepository.existsById(id)) {
            throw new NoResourceFoundException("Payment slip not found");
        }
        paymentSlipRepository.deleteById(id);
    }

    public List<PaymentSlip> getAllPaymentSlips() {
        return paymentSlipRepository.findAll();
    }
}

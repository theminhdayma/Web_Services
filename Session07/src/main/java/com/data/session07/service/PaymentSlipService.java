package com.data.session07.service;

import com.data.session07.entity.PaymentSlip;
import com.data.session07.repository.PaymentSlipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentSlipService {

    private final PaymentSlipRepository paymentSlipRepository;

    public List<PaymentSlip> findAll() {
        return paymentSlipRepository.findAll();
    }

    public PaymentSlip findById(Long id) {
        return paymentSlipRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment Slip not found with id: " + id));
    }

    public PaymentSlip save(PaymentSlip paymentSlip) {
        return paymentSlipRepository.save(paymentSlip);
    }

    public void deleteById(Long id) {
        if (!paymentSlipRepository.existsById(id)) {
            throw new RuntimeException("Payment Slip not found with id: " + id);
        }
        paymentSlipRepository.deleteById(id);
    }

    public PaymentSlip updateById(PaymentSlip paymentSlip) {
        if (!paymentSlipRepository.existsById(paymentSlip.getId())) {
            throw new RuntimeException("Payment Slip not found with id: " + paymentSlip.getId());
        }
        return paymentSlipRepository.save(paymentSlip);
    }
}

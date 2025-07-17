package com.data.session08.controller;

import com.data.session08.entity.ApiResponse;
import com.data.session08.entity.PaymentSlip;
import com.data.session08.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentslips")
public class PaymentSlipController {

    @Autowired
    private PaymentSlipService paymentSlipService;

    @PostMapping
    public ResponseEntity<ApiResponse<PaymentSlip>> addPaymentSlip(@RequestBody PaymentSlip paymentSlip) {
        PaymentSlip saved = paymentSlipService.addPaymentSlip(paymentSlip);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment slip added successfully", saved));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<PaymentSlip>> updatePaymentSlip(@PathVariable Long id, @RequestBody PaymentSlip paymentSlip) {
        PaymentSlip updated = paymentSlipService.updatePaymentSlip(id, paymentSlip);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment slip updated successfully", updated));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<PaymentSlip>>> getAllPaymentSlips() {
        List<PaymentSlip> slips = paymentSlipService.getAllPaymentSlips();
        return ResponseEntity.ok(new ApiResponse<>(true, "List of payment slips", slips));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deletePaymentSlip(@PathVariable Long id) {
        paymentSlipService.deletePaymentSlip(id);
        return ResponseEntity.ok(new ApiResponse<>(true, "Payment slip deleted successfully", null));
    }
}
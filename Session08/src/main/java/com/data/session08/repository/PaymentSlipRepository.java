package com.data.session08.repository;

import com.data.session08.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
}

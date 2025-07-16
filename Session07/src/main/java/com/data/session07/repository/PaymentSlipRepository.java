package com.data.session07.repository;

import com.data.session07.entity.PaymentSlip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PaymentSlipRepository extends JpaRepository<PaymentSlip, Long> {
    @Query("SELECT SUM(p.amount) FROM PaymentSlip p WHERE MONTH(p.createdAt) = :month AND YEAR(p.createdAt) = :year")
    Double sumExpenseByMonth(@Param("month") int month, @Param("year") int year);

    @Query("SELECT COUNT(p.id) FROM PaymentSlip p WHERE MONTH(p.createdAt) = :month AND YEAR(p.createdAt) = :year")
    Long countPaymentSlipsByMonth(@Param("month") int month, @Param("year") int year);;
}

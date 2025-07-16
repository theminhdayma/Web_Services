package com.data.session07.repository;

import com.data.session07.entity.Harvest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public interface HarvestRepository extends JpaRepository<Harvest, Long> {
    @Query("SELECT SUM(h.totalMoney) FROM Harvest h WHERE MONTH(h.createdAt) = :month AND YEAR(h.createdAt) = :year")
    Double sumPriceByMonth(@Param("month") int month, @Param("year") int year);
}

package com.data.session07.repository;

import com.data.session07.entity.Worker;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkerRepository extends JpaRepository<Worker, Long> {
    @Query("SELECT SUM(w.salary) FROM Worker w WHERE MONTH(w.salary) = :month AND YEAR(w.salary) = :year")
    Double sumSalaryByMonth(@Param("month") int month, @Param("year") int year);
}

package com.data.session08.controller;

import com.data.session08.entity.ApiResponse;
import com.data.session08.service.StatisticalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/statistics")
public class StatisticalController {

    @Autowired
    private StatisticalService statisticalService;

    @GetMapping("/top-dishes")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getTopDishes() {
        List<Map<String, Object>> result = statisticalService.getTopDishes();
        return ResponseEntity.ok(new ApiResponse<>(true, "Top 10 best-selling dishes", result));
    }

    @GetMapping("/top-customers")
    public ResponseEntity<ApiResponse<List<Map<String, Object>>>> getTopCustomers() {
        List<Map<String, Object>> result = statisticalService.getTopCustomers();
        return ResponseEntity.ok(new ApiResponse<>(true, "Top 10 highest spending customers", result));
    }

    @GetMapping("/current-month-expenses")
    public ResponseEntity<ApiResponse<Double>> getCurrentMonthExpenses() {
        Double result = statisticalService.getCurrentMonthExpenses();
        return ResponseEntity.ok(new ApiResponse<>(true, "Current month expenses", result));
    }

    @GetMapping("/monthly-expenses")
    public ResponseEntity<ApiResponse<Map<String, Double>>> getMonthlyExpenses() {
        Map<String, Double> result = statisticalService.getMonthlyExpenses();
        return ResponseEntity.ok(new ApiResponse<>(true, "Monthly expenses", result));
    }

    @GetMapping("/monthly-revenue")
    public ResponseEntity<ApiResponse<Map<String, Double>>> getMonthlyRevenue() {
        Map<String, Double> result = statisticalService.getMonthlyRevenue();
        return ResponseEntity.ok(new ApiResponse<>(true, "Monthly revenue", result));
    }

    @GetMapping("/top-employee")
    public ResponseEntity<ApiResponse<Map<String, Object>>> getTopEmployee() {
        Map<String, Object> result = statisticalService.getTopEmployee();
        return ResponseEntity.ok(new ApiResponse<>(true, "Top employee by revenue this month", result));
    }
}

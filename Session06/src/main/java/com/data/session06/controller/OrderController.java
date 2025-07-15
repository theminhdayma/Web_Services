package com.data.session06.controller;

import com.data.session06.entity.Order;
import com.data.session06.repository.OrderRepository;
import com.data.session06.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order createOrder(Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("date/{date}")
    public List<Order> getOrdersByDate(String date) {
        return orderService.getOrdersByDate(date);
    }
}

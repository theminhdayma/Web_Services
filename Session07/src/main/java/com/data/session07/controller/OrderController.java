package com.data.session07.controller;

import com.data.session07.entity.Order;
import com.data.session07.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @GetMapping
    public List<Order> getOrders() {
        return orderService.findAll();
    }

    @GetMapping("/{id}")
    public Order getOrderById(Long id) {
        return orderService.findById(id);
    }

    @PostMapping
    public Order createOrder(Order order) {
        return orderService.save(order);
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody Order order) {
        Order existingOrder = orderService.findById(id);
        if (existingOrder == null) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        order.setId(id);
        orderService.update(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        Order existingOrder = orderService.findById(id);
        if (existingOrder == null) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderService.deleteById(id);
    }

}

package com.data.session07.service;

import com.data.session07.entity.Order;
import com.data.session07.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(@PathVariable Long id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + id));
    }

    public Order save(Order order) {
        return orderRepository.save(order);
    }

    public void deleteById(@PathVariable Long id) {
        if (!orderRepository.existsById(id)) {
            throw new RuntimeException("Order not found with id: " + id);
        }
        orderRepository.deleteById(id);
    }

    public void update(Order order) {
        if (!orderRepository.existsById(order.getId())) {
            throw new RuntimeException("Order not found with id: " + order.getId());
        }
        orderRepository.save(order);
    }

}

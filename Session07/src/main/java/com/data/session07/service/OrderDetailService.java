package com.data.session07.service;

import com.data.session07.entity.Order_Detail;
import com.data.session07.repository.OrderDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public List<Order_Detail> findAll() {
        return orderDetailRepository.findAll();
    }

    public Order_Detail findById(Long id) {
        return orderDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order Detail not found with id: " + id));
    }

    public Order_Detail save(Order_Detail orderDetail) {
        return orderDetailRepository.save(orderDetail);
    }

    public void deleteById(Long id) {
        if (!orderDetailRepository.existsById(id)) {
            throw new RuntimeException("Order Detail not found with id: " + id);
        }
        orderDetailRepository.deleteById(id);
    }

    public void update(Order_Detail orderDetail) {
        if (!orderDetailRepository.existsById(orderDetail.getId())) {
            throw new RuntimeException("Order Detail not found with id: " + orderDetail.getId());
        }
        orderDetailRepository.save(orderDetail);
    }
}

package com.data.session06.repository;

import com.data.session06.entity.ProductCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductCartRepository extends JpaRepository<ProductCart, Long> {
    List<ProductCart> getProductCartByUserId(Long userId);
}

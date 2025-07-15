package com.data.session06.service;

import com.data.session06.entity.ProductCart;
import com.data.session06.repository.ProductCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductCartService {

    private final ProductCartRepository productCartRepository;

    public List<ProductCart> getProductCarts() {
        return productCartRepository.findAll();
    }

    public ProductCart getProductCartById(Long id) {
        return productCartRepository.findById(id).orElse(null);
    }

    public ProductCart createProductCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    public ProductCart updateProductCart(Long id, ProductCart productCart) {
        if (productCartRepository.existsById(id)) {
            productCart.setId(id);
            return productCartRepository.save(productCart);
        }
        return null;
    }

    public void deleteProductCart(Long id) {
        productCartRepository.deleteById(id);
    }

    public List<ProductCart> getProductCartsByUserId(Long userId) {
        return productCartRepository.getProductCartByUserId(userId);
    }
}

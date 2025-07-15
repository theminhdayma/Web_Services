package com.data.session06.controller;

import com.data.session06.entity.ProductCart;
import com.data.session06.repository.ProductCartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product-carts")
public class ProductCartController {

    private final ProductCartRepository productCartRepository;

    @GetMapping
    public List<ProductCart> getProductCarts() {
        return productCartRepository.findAll();
    }

    @GetMapping("/{id}")
    public ProductCart getProductCartById(Long id) {
        return productCartRepository.findById(id).orElse(null);
    }

    @GetMapping("/user/{userId}")
    public List<ProductCart> getProductCartsByUserId(Long userId) {
        return productCartRepository.getProductCartByUserId(userId);
    }

    @PostMapping
    public ProductCart createProductCart(ProductCart productCart) {
        return productCartRepository.save(productCart);
    }

    @PutMapping("/{id}")
    public ProductCart updateProductCart(@PathVariable Long id, @RequestBody ProductCart productCart) {
        if (productCartRepository.existsById(id)) {
            productCart.setId(id);
            return productCartRepository.save(productCart);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteProductCart(@PathVariable Long id) {
        productCartRepository.deleteById(id);
    }
}

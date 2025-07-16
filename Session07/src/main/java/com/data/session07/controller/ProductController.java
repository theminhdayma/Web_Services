package com.data.session07.controller;

import com.data.session07.entity.Product;
import com.data.session07.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(Long id) {
        return productService.findById(id);
    }

    @PostMapping
    public Product createProduct(Product product) {
        return productService.save(product);
    }

    @PutMapping("/{id}")
    public void updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        product.setId(id);
        productService.update(product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        Product existingProduct = productService.findById(id);
        if (existingProduct == null) {
            throw new RuntimeException("Product not found with id: " + id);
        }
        productService.deleteById(id);
    }
}

package com.data.session06.controller;

import com.data.session06.entity.Product;
import com.data.session06.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping
    public List<Product> getProducts(Pageable pageable, String name) {
        return productService.getAllProducts(pageable, name);
    }

    @GetMapping("/{id}")
    public Product getProductById(Long id) {
        return productService.getProductById(id);
    }

    @PostMapping
    public Product createProduct(Product product) {
        return productService.saveProduct(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody Product product) {
        return productService.updateProduct(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
}

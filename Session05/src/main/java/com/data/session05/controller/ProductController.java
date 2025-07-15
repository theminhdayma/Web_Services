package com.data.session05.controller;

import com.data.session05.entity.Product;
import com.data.session05.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @Operation(summary = "Lấy danh sách tất cả sản phẩm (JSON)")
    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Lấy danh sách sản phẩm (XML)")
    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public List<Product> getAllProductsXml() {
        return productService.getAllProducts();
    }

    @Operation(summary = "Lấy thông tin sản phẩm theo ID")
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(
            @Parameter(description = "ID của sản phẩm") @PathVariable Long id) {
        Product product = productService.getProductById(id);
        return (product != null) ? ResponseEntity.ok(product) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Tạo sản phẩm mới")
    @PostMapping
    public Product createProduct(
            @RequestBody Product product) {
        return productService.createProduct(product);
    }

    @Operation(summary = "Cập nhật thông tin sản phẩm")
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(
            @Parameter(description = "ID sản phẩm cần cập nhật") @PathVariable Long id,
            @RequestBody Product product) {
        Product updated = productService.updateProduct(id, product);
        return (updated != null) ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Xóa sản phẩm theo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @Parameter(description = "ID sản phẩm cần xóa") @PathVariable Long id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }
}

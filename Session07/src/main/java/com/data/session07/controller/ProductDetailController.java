package com.data.session07.controller;

import com.data.session07.entity.ProductDetail;
import com.data.session07.service.ProductDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product-detail")
public class ProductDetailController {

    private final ProductDetailService productDetailService;

    @GetMapping
    public List<ProductDetail> getProductDetails() {
        return productDetailService.getProductDetails();
    }

    @GetMapping("/{id}")
    public ProductDetail getProductDetail(@PathVariable Long id) {
        return productDetailService.getProductDetail(id);
    }

    @PostMapping
    public ProductDetail createProductDetail(@RequestBody ProductDetail productDetail) {
        return productDetailService.createProductDetail(productDetail);
    }

    @PutMapping("/{id}")
    public ProductDetail updateProductDetail(@PathVariable Long id, @RequestBody ProductDetail productDetail) {
        return productDetailService.updateProductDetail(id, productDetail);
    }

    @DeleteMapping("/{id}")
    public void deleteProductDetail(@PathVariable Long id) {
        productDetailService.deleteProductDetail(id);
    }
}

package com.data.session07.service;

import com.data.session07.entity.ProductDetail;
import com.data.session07.repository.ProductDetailRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductDetailService {

    private final ProductDetailRepository productDetailRepository;

    public List<ProductDetail> getProductDetails() {
        return productDetailRepository.findAll();
    }

    public ProductDetail getProductDetail(Long id) {
        return productDetailRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProductDetail not found with id: " + id));
    }

    public ProductDetail createProductDetail(ProductDetail productDetail) {
        return productDetailRepository.save(productDetail);
    }

    public ProductDetail updateProductDetail(Long id, ProductDetail productDetail) {
        if (!productDetailRepository.existsById(id)) {
            throw new RuntimeException("ProductDetail not found with id: " + id);
        }
        productDetail.setId(id);
        return productDetailRepository.save(productDetail);
    }

    public void deleteProductDetail(Long id) {
        if (!productDetailRepository.existsById(id)) {
            throw new RuntimeException("ProductDetail not found with id: " + id);
        }
        productDetailRepository.deleteById(id);
    }
}

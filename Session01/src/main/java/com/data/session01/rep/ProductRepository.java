package com.data.session01.rep;

import com.data.session01.entity.Product;

import java.util.List;

public interface ProductRepository {
     List<Product> findAll();
     Product findById(Long id);
     boolean save(Product product);
     boolean update(Product product);
     boolean deleteById(Long id);
}

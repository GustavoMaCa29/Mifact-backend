package com.example.mifact_test.services.interfaces;

import com.example.mifact_test.repository.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getProducts();
    Product findById(Long id);
    List<Product> findByName(String name);
    Product createProduct(Product product);
    Product updateById(Product product, Long id);
    void deleteById(Long id);

}

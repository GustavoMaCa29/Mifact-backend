package com.example.mifact_test.services.impl;

import com.example.mifact_test.constants.ErrorMessages;
import com.example.mifact_test.repository.entity.Product;
import com.example.mifact_test.repository.interfaces.ProductRepository;
import com.example.mifact_test.services.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.getProducts();
        return products.stream()
                .sorted(Comparator.comparing(Product::getCreationDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Product findById(Long id) {

        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            return optionalProduct.get();
        } else {
            throw new NoSuchElementException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
    }

    @Override
    public List<Product> findByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product createProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product updateById(Product product, Long id) {
        Product updateProduct = productRepository.findById(id).orElseThrow(()-> new RuntimeException(ErrorMessages.PRODUCT_NOT_FOUND));

        if (product.getPrice() < 0 || product.getQuantity() < 0) {
            throw new IllegalArgumentException(ErrorMessages.INCORRECT_AMOUNT);
        }

        updateProduct.setName(product.getName());
        updateProduct.setDescription(product.getDescription());
        updateProduct.setPrice(product.getPrice());
        updateProduct.setQuantity(product.getQuantity());
        updateProduct = productRepository.save(updateProduct);
        return updateProduct;
    }

    @Override
    public void deleteById(Long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            product.setIsDeleted(true);
            productRepository.save(product);
        } else {
            throw new EntityNotFoundException(ErrorMessages.PRODUCT_NOT_FOUND);
        }
    }
}

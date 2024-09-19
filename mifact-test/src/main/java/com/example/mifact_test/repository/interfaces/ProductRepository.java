package com.example.mifact_test.repository.interfaces;

import com.example.mifact_test.repository.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT p FROM Product p WHERE p.isDeleted = false ORDER BY p.creationDate DESC")
    List<Product> getProducts();

    @Query("SELECT p FROM Product p WHERE p.name LIKE %:name% AND p.isDeleted = false")
    List<Product> findByName(String name);

    @Query("SELECT p FROM Product p WHERE p.id = :id AND p.isDeleted = false")
    Optional<Product> findById(@Param("id") Long id);
}
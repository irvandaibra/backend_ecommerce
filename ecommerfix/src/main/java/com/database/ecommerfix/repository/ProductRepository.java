package com.database.ecommerfix.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.database.ecommerfix.model.Product;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

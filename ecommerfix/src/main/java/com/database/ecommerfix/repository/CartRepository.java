package com.database.ecommerfix.repository;

import com.database.ecommerfix.model.Cart;
import com.database.ecommerfix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    List<Cart> findALlByUserOrderByCreatedDateDesc(User user);

}

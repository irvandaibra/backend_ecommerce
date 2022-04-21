package com.database.ecommerfix.repository;

import com.database.ecommerfix.model.AuthToken;
import com.database.ecommerfix.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<AuthToken, Integer> {
    AuthToken findTokenByUser(User user);
    AuthToken findTokenByToken(String token);
}

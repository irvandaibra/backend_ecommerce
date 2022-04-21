package com.database.ecommerfix.service;

import com.database.ecommerfix.model.AuthToken;
import com.database.ecommerfix.model.User;
import com.database.ecommerfix.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class AuthTokenSer {

    @Autowired
    TokenRepository tokenRepository;


    public void saveConfirmationToken(AuthToken authToken) {
        tokenRepository.save(authToken);
    }

    public AuthToken getToken(User user) {
        return tokenRepository.findTokenByUser(user);
    }
}

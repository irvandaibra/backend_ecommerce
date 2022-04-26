package com.database.ecommerfix.service;

import com.database.ecommerfix.excep.AuthFailException;
import com.database.ecommerfix.model.AuthToken;
import com.database.ecommerfix.model.User;
import com.database.ecommerfix.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;


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

    public User getUser(String token) {
      final AuthToken authToken = tokenRepository.findByToken(token);
      if (Objects.isNull(token)) {
          return null;
      }
      return authToken.getUser();
    }

    public void authenticate(String token) throws AuthFailException {
        if (Objects.isNull(token)) {
            throw new AuthFailException("token no present");
        }
        if (Objects.isNull(getUser(token))) {
            throw new AuthFailException("token not valid");
        }
    }
}

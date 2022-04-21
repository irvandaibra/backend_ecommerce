package com.database.ecommerfix.service;


import com.database.ecommerfix.dto.ResponseDto;
import com.database.ecommerfix.dto.user.SigninDto;
import com.database.ecommerfix.dto.user.SigninResponseDto;
import com.database.ecommerfix.dto.user.SignupDto;
import com.database.ecommerfix.excep.AuthFailException;
import com.database.ecommerfix.excep.CustomException;
import com.database.ecommerfix.model.AuthToken;
import com.database.ecommerfix.model.User;
import com.database.ecommerfix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

@Service
public class UserSer {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthTokenSer authTokenSer;


    @Transactional
    
    public ResponseDto signup(SignupDto signupDto) {
        //check if user is already present
        if (Objects.nonNull(userRepository.findByEmail(signupDto.getEmail()))) {
            //we have an user
            throw new CustomException("user already present");
        }


        //has the password

        String encryptedpassword = signupDto.getPassword();
        try {
            encryptedpassword = hashPassword(signupDto.getPassword());
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        
        User user = new User(signupDto.getFirstName(), signupDto.getLastName(),
                signupDto.getEmail(),  encryptedpassword);

        userRepository.save(user);
        //created token

        final AuthToken authToken = new AuthToken(user);

        authTokenSer.saveConfirmationToken(authToken);

        
        ResponseDto responseDto = new ResponseDto("succes", "dummy message");
        return responseDto;
    }

    private String hashPassword(String password) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String hash = DatatypeConverter.printHexBinary(digest).toUpperCase();
        return hash;
    }

    public SigninResponseDto signin(SigninDto signinDto) {
        //find byEmail
        User user = userRepository.findByEmail(signinDto.getEmail());
        if (Objects.isNull(user)) {
            throw new AuthFailException("user is not valid");
        }
        try {
           if (!user.getPassword().equals(hashPassword(signinDto.getPassword()))) {
                throw new AuthFailException("wrong password");
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        AuthToken token = authTokenSer.getToken(user);

        if (Objects.isNull(token)) {
            throw new CustomException("token is not present");
        }
        return new SigninResponseDto("succes", token.getToken());
    }
}

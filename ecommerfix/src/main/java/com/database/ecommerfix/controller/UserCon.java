package com.database.ecommerfix.controller;

import com.database.ecommerfix.dto.ResponseDto;
import com.database.ecommerfix.dto.user.SigninDto;
import com.database.ecommerfix.dto.user.SigninResponseDto;
import com.database.ecommerfix.dto.user.SignupDto;
import com.database.ecommerfix.service.UserSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user")
@RestController
public class UserCon {

    @Autowired
    UserSer userSer;

    // two pis

    //sign up
    @PostMapping("/signup")
    public ResponseDto signup(@RequestBody SignupDto signupDto) {
    return userSer.signup(signupDto);

    }

    @PostMapping("/signin")
    public SigninResponseDto signin(@RequestBody SigninDto signinDto){
        return userSer.signin(signinDto);
    }


    //sign in
}

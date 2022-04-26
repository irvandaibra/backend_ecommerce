package com.database.ecommerfix.controller;

import com.database.ecommerfix.common.ApiResponse;
import com.database.ecommerfix.dto.ProductDto;
import com.database.ecommerfix.model.AuthToken;
import com.database.ecommerfix.model.Product;
import com.database.ecommerfix.model.User;
import com.database.ecommerfix.model.WishList;
import com.database.ecommerfix.service.AuthTokenSer;
import com.database.ecommerfix.service.WishListSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/wishlist")
public class WishListCon {

    @Autowired
    WishListSer wishListSer;

    @Autowired
    AuthTokenSer authTokenSer;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addWishList(@RequestBody Product product,
                                                   @RequestParam ("token") String token) {

        authTokenSer.authenticate(token);

        User user = authTokenSer.getUser(token);

        WishList wishList = new WishList(user, product);

        wishListSer.createWishList(wishList);

        ApiResponse apiResponse = new ApiResponse( true, "added to wishlist");
        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getWishList(@PathVariable ("token") String token) {

        authTokenSer.authenticate(token);

        User user = authTokenSer.getUser(token);

        List<ProductDto> productDtos = wishListSer.getWishListForUser(user);

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }

}
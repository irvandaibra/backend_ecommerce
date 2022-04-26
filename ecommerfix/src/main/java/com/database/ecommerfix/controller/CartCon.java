package com.database.ecommerfix.controller;

import com.database.ecommerfix.common.ApiResponse;
import com.database.ecommerfix.dto.cart.AddCartDto;
import com.database.ecommerfix.dto.cart.CartDto;
import com.database.ecommerfix.model.AuthToken;
import com.database.ecommerfix.model.User;
import com.database.ecommerfix.service.AuthTokenSer;
import com.database.ecommerfix.service.CartSer;
import com.database.ecommerfix.service.ProductSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartCon {

    @Autowired
   private CartSer cartSer;

    @Autowired
private     AuthTokenSer authTokenSer;

    @Autowired
    private ProductSer productSer;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addToCart(@RequestBody AddCartDto addCartDto, @RequestParam("token") String token) {

        authTokenSer.authenticate(token);

        User user = authTokenSer.getUser(token);

        cartSer.addCartDto(addCartDto, user);

        return new ResponseEntity<>(new ApiResponse(true, "added to cart"), HttpStatus.CREATED);
    }

    @GetMapping("/")
    public ResponseEntity<CartDto> getCartItems(@RequestParam("token") String token) {

        authTokenSer.authenticate(token);

        User user = authTokenSer.getUser(token);

        CartDto cartDto = cartSer.listCartItems(user);
        return new ResponseEntity<>(cartDto, HttpStatus.OK);
    }

    @DeleteMapping("/{cartItemId}")
    public ResponseEntity<ApiResponse> deleteCartItem(@PathVariable("cartItemId") Integer itemId,
                                                      @RequestParam("token") String token) {
        authTokenSer.authenticate(token);

        User user = authTokenSer.getUser(token);

        cartSer.deleteCartItem(itemId, user);

        return new ResponseEntity<>(new ApiResponse(true, "item has been removed"), HttpStatus.OK);
    }
}

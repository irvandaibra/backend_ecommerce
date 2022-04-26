package com.database.ecommerfix.controller;

import com.database.ecommerfix.dto.checkout.CheckoutItemDto;
import com.database.ecommerfix.dto.checkout.StripeResponse;
import com.database.ecommerfix.service.AuthTokenSer;
import com.database.ecommerfix.service.OrderSer;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderCon {
    @Autowired
    private AuthTokenSer authTokenSer;

    @Autowired
    private OrderSer orderSer;

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutList(@RequestBody List<CheckoutItemDto> checkoutItemDtoList)
    throws StripeException {
        Session session = orderSer.createSession(checkoutItemDtoList);
        StripeResponse stripeResponse = new StripeResponse(session.getId());
        return new ResponseEntity<>(stripeResponse, HttpStatus.OK);
    }

}

package com.database.ecommerfix.service;

import com.database.ecommerfix.dto.cart.AddCartDto;
import com.database.ecommerfix.dto.cart.CartDto;
import com.database.ecommerfix.dto.cart.CartItemDto;
import com.database.ecommerfix.excep.CustomException;
import com.database.ecommerfix.model.Cart;
import com.database.ecommerfix.model.Product;
import com.database.ecommerfix.model.User;
import com.database.ecommerfix.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class CartSer {

    @Autowired
    ProductSer productSer;

    @Autowired
    CartRepository cartRepository;
    private Integer cartItemId;

    public void addCartDto(AddCartDto addCartDto, User user) {
        Product product = productSer.findById(addCartDto.getProductId());

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setUser(user);
        cart.setQuantity(addCartDto.getQuantity());
        cart.setCreatedDate(new Date());

        cartRepository.save(cart);
    }


    public CartDto listCartItems(User user) {
    List<Cart> cartList =  cartRepository.findALlByUserOrderByCreatedDateDesc(user);

    List<CartItemDto> cartItems= new ArrayList<>();
    double totalCost = 0;
    for (Cart cart : cartList) {
        CartItemDto cartItemDto = new CartItemDto(cart);
        totalCost += cartItemDto.getQuantity() * cart.getProduct().getHarga();
        cartItems.add(cartItemDto);
    }
    CartDto cartDto = new CartDto();
    cartDto.setTotalCost(totalCost);
    cartDto.setCartItems(cartItems);
    return cartDto;
    }

    public void deleteCartItem(Integer itemId, User user) {
        Optional<Cart> optionalCart = cartRepository.findById(cartItemId);

        if (optionalCart.isEmpty()) {
            throw new CustomException("cart item id is invalid:" + cartItemId);

        }

        Cart cart = optionalCart.get();

        if (cart.getUser() != user) {
            throw new CustomException("cart items is does not belong to user: " + cartItemId);
        }

        cartRepository.delete(cart);
    }

}

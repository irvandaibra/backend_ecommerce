package com.database.ecommerfix.service;

import com.database.ecommerfix.dto.ProductDto;
import com.database.ecommerfix.model.User;
import com.database.ecommerfix.model.WishList;
import com.database.ecommerfix.repository.WishListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WishListSer {

    @Autowired
    static
    WishListRepository wishListRepository;

    @Autowired
    ProductSer productSer;

    public static void createWishList(WishList wishList) {
        wishListRepository.save(wishList);
    }

    public List<ProductDto> getWishListForUser(User user) {
       final List<WishList> wishLists =  wishListRepository.findAllByUserOrderByCreatedDateDesc(user);
        List<ProductDto> productDtos = new ArrayList<>();
        for (WishList wishList: wishLists) {
                productDtos.add(productSer.getProductDto(wishList.getProduct()));
        }
        return productDtos;
    }
}

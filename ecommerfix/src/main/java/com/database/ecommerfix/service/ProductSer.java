package com.database.ecommerfix.service;

import com.database.ecommerfix.dto.ProductDto;
import com.database.ecommerfix.model.Category;
import com.database.ecommerfix.model.Product;
import com.database.ecommerfix.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductSer {
    @Autowired
    ProductRepository productRepository;

    public void createProduct(ProductDto productDto, Category category) {
        Product product = new Product();
        product.setNamaProduct(productDto.getNamaProduct());
        product.setDesc(productDto.getDesc());
        product.setImgUrl(productDto.getImgUrl());
        product.setHarga(productDto.getHarga());
        product.setCategory(category);
        productRepository.save(product);
    }


    public ProductDto getProductDto(Product product) {
        Product productDto = new Product();
        productDto.setNamaProduct(product.getNamaProduct());
        productDto.setDesc(product.getDesc());
        productDto.setImgUrl(product.getImgUrl());
        productDto.setHarga(product.getHarga());
        productDto.setCategory_id(product.getCategory().getId());
        return productDto;

    }

    public List<ProductDto> getAllProducts() {
        List<Product> allProducts = productRepository.findAll();
        List<ProductDto> productDtos = new ArrayList<>();
        for (Product product: allProducts) {
            productDtos.add(getProductDto(product));
        }
        return productDtos;
    }
    
}

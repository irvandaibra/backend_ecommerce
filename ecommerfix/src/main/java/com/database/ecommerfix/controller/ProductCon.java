package com.database.ecommerfix.controller;

import com.database.ecommerfix.common.ApiResponse;
import com.database.ecommerfix.dto.ProductDto;
import com.database.ecommerfix.model.Category;
import com.database.ecommerfix.model.Product;
import com.database.ecommerfix.repository.CategoryRepository;
import com.database.ecommerfix.repository.ProductRepository;
import com.database.ecommerfix.service.ProductSer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductCon {

    @Autowired
    private ProductSer productSer;

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping
    public  ResponseEntity<List<ProductDto>> getProduct() {
        List<ProductDto> products = productSer.getAllProducts();
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    // tambah product
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> createProduct(@RequestBody ProductDto productDto) {
      Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
      if (!optionalCategory.isPresent()){
          return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
      }
      productSer.createProduct(productDto, optionalCategory.get());
      return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

    // edit product
    @PatchMapping("/edit/{productId}")
    public ResponseEntity<ApiResponse> updateProduct(@PathVariable ("productId") Integer productId, @RequestBody ProductDto productDto) throws Exception {
        Optional<Category> optionalCategory = categoryRepository.findById(productDto.getCategory_id());
        if (!optionalCategory.isPresent()){
            return new ResponseEntity<ApiResponse>(new ApiResponse(false, "category does not exist"), HttpStatus.BAD_REQUEST);
        }
        productSer.updateProduct(productDto, productId);
        return new ResponseEntity<ApiResponse>(new ApiResponse(true, "product has been added"), HttpStatus.CREATED);
    }

}
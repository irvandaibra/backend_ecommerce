package com.database.ecommerfix.controller;

import com.database.ecommerfix.common.ApiResponse;
import com.database.ecommerfix.model.Category;
import com.database.ecommerfix.service.CategorySer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/category")
public class CategoryCon {

    @Autowired
    CategorySer categoryser;

    @PostMapping
    public ResponseEntity<ApiResponse> createCategory(@RequestBody Category category) {
        categoryser.createCategory(category);
        return new ResponseEntity<ApiResponse>(new ApiResponse( true, "a new category created"), HttpStatus.CREATED);

    }

    @GetMapping
    public List<Category> listCategory() {
       return categoryser.listCategory();
    }

    @PatchMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> updateCategory(@PathVariable("categoryId") int categoryId, @RequestBody Category category) {
        System.out.println("category id" + categoryId);
        if (!categoryser.findById(categoryId)) {
            return new ResponseEntity<ApiResponse>(new ApiResponse( false, "category does not exists"), HttpStatus.NOT_FOUND);
        }
        categoryser.editCategory(categoryId, category);
        return new ResponseEntity<ApiResponse>(new ApiResponse( true, "a new category created"), HttpStatus.OK);    }

}

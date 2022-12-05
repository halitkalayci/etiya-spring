package com.etiya.ecommercedemo.api.controllers;

import com.etiya.ecommercedemo.business.abstracts.CategoryService;
import com.etiya.ecommercedemo.business.dtos.request.category.AddCategoryRequest;
import com.etiya.ecommercedemo.business.dtos.response.category.AddCategoryResponse;
import com.etiya.ecommercedemo.core.entities.concretes.Category;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@AllArgsConstructor
public class CategoriesController {
    private CategoryService categoryService;

    // GetCategoryResponse => id,name
    @GetMapping("")
    public List<Category> getAll(){
        return categoryService.getAll();
    }


    // client
    // server
    // DTO => Data Transfer Object
    // AddCategoryRequest => name,type
    // ResponseEntity
    @PostMapping("/add")
    public ResponseEntity<AddCategoryResponse> addCategory(@RequestBody AddCategoryRequest addCategoryRequest){
        return new ResponseEntity<AddCategoryResponse>(categoryService.addCategory(addCategoryRequest), HttpStatus.CREATED);
    }
}

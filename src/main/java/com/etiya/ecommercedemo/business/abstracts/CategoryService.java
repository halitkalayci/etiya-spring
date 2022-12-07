package com.etiya.ecommercedemo.business.abstracts;

import com.etiya.ecommercedemo.business.dtos.request.category.AddCategoryRequest;
import com.etiya.ecommercedemo.business.dtos.response.category.AddCategoryResponse;
import com.etiya.ecommercedemo.entities.concretes.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(int id);
    AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest);
}

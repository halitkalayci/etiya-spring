package com.etiya.ecommercedemo.business.abstracts;

import com.etiya.ecommercedemo.entities.concretes.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();
    Category getById(int id);
}

package com.etiya.ecommercedemo.business.abstracts;

import com.etiya.ecommercedemo.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(int id);
}

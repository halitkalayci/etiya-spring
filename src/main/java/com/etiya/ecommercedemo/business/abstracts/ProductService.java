package com.etiya.ecommercedemo.business.abstracts;

import com.etiya.ecommercedemo.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemo.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
    Product getById(int id);
    List<Product> getAllByStockGreaterThan(int stock);
    Product getByName(String name);
    Product addProduct(AddProductRequest addProductRequest);

    List<Product> getAllByUnitPriceGreaterThan(double unitPrice);
    List<Product> getAllOrderByStock();

    List<Product> findByExample();
    List<Product> findByCategoryId(int category_id);
}

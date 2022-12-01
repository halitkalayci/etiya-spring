package com.etiya.ecommercedemo.business.concretes;

import com.etiya.ecommercedemo.business.abstracts.ProductService;
import com.etiya.ecommercedemo.entities.concretes.Product;
import com.etiya.ecommercedemo.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private ProductRepository productRepository;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).orElseThrow();
    }
}

package com.etiya.ecommercedemo.repository.abstracts;

import com.etiya.ecommercedemo.entities.concretes.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Integer> {
}

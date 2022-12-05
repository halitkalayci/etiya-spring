package com.etiya.ecommercedemo.repository.abstracts;

import com.etiya.ecommercedemo.core.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    // ÖZEL QUERYLER
}

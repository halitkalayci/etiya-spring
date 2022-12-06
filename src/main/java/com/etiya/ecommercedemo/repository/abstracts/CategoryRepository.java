package com.etiya.ecommercedemo.repository.abstracts;

import com.etiya.ecommercedemo.core.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Integer> {
    // ÖZEL QUERYLER
    List<Category> findByNameEquals(String name);

    boolean existsCategoryByName(String name);
    // TEK BİR SONUÇ BULMASI İÇİN
}

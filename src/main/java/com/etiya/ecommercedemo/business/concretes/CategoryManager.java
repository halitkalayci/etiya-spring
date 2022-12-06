package com.etiya.ecommercedemo.business.concretes;

import com.etiya.ecommercedemo.business.abstracts.CategoryService;
import com.etiya.ecommercedemo.business.dtos.request.category.AddCategoryRequest;
import com.etiya.ecommercedemo.business.dtos.response.category.AddCategoryResponse;
import com.etiya.ecommercedemo.core.entities.concretes.Category;
import com.etiya.ecommercedemo.repository.abstracts.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        // SAYFALAMA
        // FİLTRELEME
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.findById(id).orElseThrow();
    }

    // JPA Repository SAVE methodu, eklenen veriyi geri döner.
    @Override
    public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        // MAPPING => AUTO MAPPER
        // TODO: Implement auto mapper.
        Category category = new Category();
        category.setName(addCategoryRequest.getName());
        category.setType(addCategoryRequest.getType());
        //
        // Business Rules
        // Veritabanında aynı isimde iki kategori bulunamaz.
        categoryCanNotExistWithSameName(addCategoryRequest.getName());

        // Validation
        Category savedCategory = categoryRepository.save(category);

        // MAPPING -> Category => AddCategoryResponse
        AddCategoryResponse response =
                new AddCategoryResponse(savedCategory.getId(), savedCategory.getName(), savedCategory.getType());
        //
        return response;
    }
    private void categoryCanNotExistWithSameName(String name){
        // Exception fırlatma
       boolean isExists = categoryRepository.existsCategoryByName(name);
       if(isExists) // Veritabanımda bu isimde bir kategori mevcut!!
           // TODO: Add custom business exception.
           // TODO: Remove magic string
           // TODO: Add global exception handler
           throw new RuntimeException("Bu isimle bir kategori zaten mevcut!");
    }
}

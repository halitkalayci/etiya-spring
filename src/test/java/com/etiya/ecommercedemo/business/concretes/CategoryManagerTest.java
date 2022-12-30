package com.etiya.ecommercedemo.business.concretes;

import com.etiya.ecommercedemo.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemo.core.util.mapping.ModelMapperManager;
import com.etiya.ecommercedemo.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemo.entities.concretes.Category;
import com.etiya.ecommercedemo.repository.abstracts.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.support.ResourceBundleMessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class CategoryManagerTest {

    CategoryManager categoryManager;

    // MOCK
    CategoryRepository categoryRepository;

    ModelMapperService modelMapperService;
    MessageSource messageSource;

    @BeforeEach
    void setUp() {
        // Mocking - Mockito
        categoryRepository = mock(CategoryRepository.class);
        modelMapperService = new ModelMapperManager(new ModelMapper());
        messageSource = new ResourceBundleMessageSource();

        categoryManager = new CategoryManager(categoryRepository,modelMapperService,messageSource);
    }

    @Test
    void getAll() {
        // Repository -> FindAll metodunu doldur...
        List<Category> categoriesToReturn = new ArrayList<>();
        categoriesToReturn.add(Category.builder().id(1).name("Kategori 1").type("Giyim").build());

        when(categoryRepository.findAll()).thenReturn(categoriesToReturn);
        // categoryRepository.findAll() -> 1 adet kategori olan liste
        List<Category> actualResult = categoryManager.getAll();
        assert categoriesToReturn.size() == actualResult.size(); // listedeki tüm elemanlar eşit mi aynı mı??
    }

    @Test
    void getById() {
        Category categoryToReturn = Category.builder().name("Kategori 1").id(1).type("Giyim").build();
        Optional<Category> category = Optional.of(categoryToReturn);
        when(categoryRepository.findById(1)).thenReturn(category);

        Category actualCategory = categoryManager.getById(1);
        assert actualCategory.equals(categoryToReturn);
    }

    @Test
    void getById_NotExist_ShouldThrowException(){
        // exception fırlatma testi..
        assertThrows(BusinessException.class, () -> {
            categoryManager.getById(2);
        });
    }

    @Test
    void addCategory() {

    }
}
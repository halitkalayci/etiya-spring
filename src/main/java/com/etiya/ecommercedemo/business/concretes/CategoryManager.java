package com.etiya.ecommercedemo.business.concretes;

import com.etiya.ecommercedemo.business.abstracts.CategoryService;
import com.etiya.ecommercedemo.business.constants.Messages;
import com.etiya.ecommercedemo.business.dtos.request.category.AddCategoryRequest;
import com.etiya.ecommercedemo.business.dtos.response.category.AddCategoryResponse;
import com.etiya.ecommercedemo.core.util.exceptions.BusinessException;
import com.etiya.ecommercedemo.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemo.entities.concretes.Category;
import com.etiya.ecommercedemo.repository.abstracts.CategoryRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CategoryManager implements CategoryService {

    private CategoryRepository categoryRepository;
    private ModelMapperService modelMapperService;
    private MessageSource messageSource;

    @Override
    public List<Category> getAll() {
        // SAYFALAMA
        // FİLTRELEME
        return categoryRepository.findAll();
    }

    @Override
    public Category getById(int id) {
        return categoryRepository.findById(id).orElseThrow(() -> new BusinessException("Böyle bir kategori yok.."));
    }

    // JPA Repository SAVE methodu, eklenen veriyi geri döner.
    @Override
    public AddCategoryResponse addCategory(AddCategoryRequest addCategoryRequest) {
        // MAPPING => AUTO MAPPER
           // MANUAL MAPPING
           // Category category = new Category();
           // category.setName(addCategoryRequest.getName());
           // category.setType(addCategoryRequest.getType());
           //
        Category category = modelMapperService.getMapper().map(addCategoryRequest,Category.class);
        //
        // Business Rules
        // Veritabanında aynı isimde iki kategori bulunamaz.
        categoryCanNotExistWithSameName(addCategoryRequest.getName());

        // Validation
        Category savedCategory = categoryRepository.save(category);

        AddCategoryResponse response = modelMapperService.getMapper().map(savedCategory,AddCategoryResponse.class);
        return response;
    }
    private void categoryCanNotExistWithSameName(String name){
        // Exception fırlatma
       boolean isExists = categoryRepository.existsCategoryByName(name);
       if(isExists) // Veritabanımda bu isimde bir kategori mevcut!!
           // TODO: Change all business rules to throw BusinessException
           throw new BusinessException(
                   messageSource.getMessage
                           (Messages.Category.CategoryExistsWithSameName,null, LocaleContextHolder.getLocale())
           );
    }
}

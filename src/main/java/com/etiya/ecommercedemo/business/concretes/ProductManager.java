package com.etiya.ecommercedemo.business.concretes;

import com.etiya.ecommercedemo.business.abstracts.CategoryService;
import com.etiya.ecommercedemo.business.abstracts.ProductService;
import com.etiya.ecommercedemo.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemo.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemo.entities.concretes.Product;
import com.etiya.ecommercedemo.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {
    private ProductRepository productRepository;
    // CategoryService,
    private CategoryService categoryService;
    private ModelMapperService modelMapperService;
    @Override
    public List<Product> getAll() {
        return productRepository.findAll();
    }

    @Override
    public Product getById(int id) {
        return productRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Product> getAllByStockGreaterThan(int stock) {
        return productRepository.findAllProductsByStockGreaterThanOrderByStockDesc(stock);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    public Product addProduct(AddProductRequest addProductRequest) {
            /* MANUAL MAPPING
            Product product = new Product();
            product.setName(addProductRequest.getName());
            product.setUnit_price(addProductRequest.getUnit_price());
            product.setStock(addProductRequest.getStock());
            product.setProduct_rating(addProductRequest.getProduct_rating());
            // Category
            Category category = categoryService.getById(addProductRequest.getCategory_id());
            product.setCategory(category);
            */

        Product product = modelMapperService.getMapper().map(addProductRequest,Product.class);
        return productRepository.save(product);
    }
}

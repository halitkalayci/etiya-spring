package com.etiya.ecommercedemo.business.concretes;

import com.etiya.ecommercedemo.business.abstracts.CategoryService;
import com.etiya.ecommercedemo.business.abstracts.ProductService;
import com.etiya.ecommercedemo.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemo.business.dtos.response.product.ListProductResponse;
import com.etiya.ecommercedemo.core.util.mapping.ModelMapperService;
import com.etiya.ecommercedemo.entities.concretes.Category;
import com.etiya.ecommercedemo.entities.concretes.Product;
import com.etiya.ecommercedemo.repository.abstracts.CategoryRepository;
import com.etiya.ecommercedemo.repository.abstracts.ProductRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
public class ProductManager implements ProductService {

    private ProductRepository productRepository;
    // CategoryService,
    private CategoryService categoryService;
    private CategoryRepository categoryRepository;
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
        return productRepository.findByStockGreaterThan(stock);
    }

    @Override
    public Product getByName(String name) {
        return productRepository.findByName(name);
    }

    @Override
    @Transactional
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

        // Ürün eklemeden kategori eklenecek..
        // Eğer kategori eklenmez ise ya da ürün eklenemezse tüm işlemler iptal edilecek..
        Category category = Category
                .builder()
                .name("Deneme Transaction")
                .type("Deneme Type")
                .build();
        categoryRepository.save(category);
        // submitOrder() -> List<Product> -> KullanıcıId
        // Kullanıcının sepetini getir
        // Sepetteki tüm ürünleri getir.
        // Tüm ürünleri tek tek order ile bağlayacak veriyi ekle.

        Product product = modelMapperService.getMapper().map(addProductRequest,Product.class);
        //
        // KOD
        // JUnit5
        //
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllByUnitPriceGreaterThan(double unitPrice) {
        return productRepository.findByUnitPriceGreaterThan(unitPrice);
    }

    @Override
    public List<Product> getAllOrderByStock() {
        return productRepository.findAllByOrderByStock();
    }

    @Override
    public List<Product> findByExample() {
        return productRepository.findByExample();
    }

    @Override
    public List<Product> findByCategoryId(int category_id) {
        return productRepository.findAllByCategory_Id(category_id);
    }

    @Override
    public Page<Product> findAllWithPagination(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Slice<Product> findAllWithSlice(Pageable pageable) {
        return productRepository.getAllWithSlice(pageable);
    }

    @Override
    public Page<ListProductResponse> findAllWithPaginationDto(Pageable pageable) {
        return productRepository.getAllListProducts(pageable);
    }
}

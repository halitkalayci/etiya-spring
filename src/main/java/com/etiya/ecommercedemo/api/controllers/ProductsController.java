package com.etiya.ecommercedemo.api.controllers;

import com.etiya.ecommercedemo.business.abstracts.ProductService;
import com.etiya.ecommercedemo.business.constants.Paths;
import com.etiya.ecommercedemo.business.dtos.request.product.AddProductRequest;
import com.etiya.ecommercedemo.business.dtos.response.product.ListProductResponse;
import com.etiya.ecommercedemo.entities.concretes.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Paths.apiPrefix + "products")
public class ProductsController {
    // DI
    @Autowired
    private ProductService productService;


    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/getAll")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @GetMapping("/getByUnitPriceGreaterThan")
    public List<Product> getByUnitPriceGreaterThan(@RequestParam("unitPrice") double unitPrice){
        return productService.getAllByUnitPriceGreaterThan(unitPrice);
    }

    @GetMapping("/getAllOrderByStock")
    public List<Product> getAllOrderByStock(){
        return productService.getAllOrderByStock();
    }

    @GetMapping("/getByCategoryId")
    public List<Product> getByCategoryId(@RequestParam("categoryId") int category_id){
        return productService.findByCategoryId(category_id);
    }
    @GetMapping("/getExample")
    public List<Product> getExample(){
        return productService.findByExample();
    }
    @GetMapping("/getById")
    public Product getById(@RequestParam("id") int id){
        return productService.getById(id);
    }
    @GetMapping("{id}")
    public Product getByIdPath(@PathVariable int id){
        return productService.getById(id);
    }

    @GetMapping("/getByStockGreaterThan")
    public List<Product> getAllByStock(@RequestParam("stock") int stock){
        return productService.getAllByStockGreaterThan(stock);
    }

    @GetMapping("/getByName")
    public Product getByName(@RequestParam("name") String name){
        return productService.getByName(name);
    }

    @PostMapping()
    public Product add(@RequestBody @Valid AddProductRequest addProductRequest){
        return productService.addProduct(addProductRequest);
    }

    @GetMapping("/getWithPagination")
    // RequestParam => page,pageSize
    public Page<Product> getWithPagination(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        return productService.findAllWithPagination(pageable);
    }

    @GetMapping("/getWithSlice")
    // RequestParam => page,pageSize
    public Slice<Product> getWithSlice(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        return productService.findAllWithSlice(pageable);
    }

    @GetMapping("/getWithPaginationDto")
    // RequestParam => page,pageSize
    public Page<ListProductResponse>
                getWithPaginationDto(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize){
        Pageable pageable = PageRequest.of(page,pageSize);
        return productService.findAllWithPaginationDto(pageable);
    }
}

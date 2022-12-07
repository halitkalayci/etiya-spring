package com.etiya.ecommercedemo.business.dtos.request.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AddProductRequest {
    @NotNull(message = "Ürün ismi boş olamaz.")
    @NotBlank(message = "Ürün ismi boş olamaz.")
    private String name;

    private double unit_price;

    @Min(value=0,message = "Stok adeti 0'dan küçük olamaz.")
    private int stock;

    // Product => Category
    // categoryId
    // categoryName
    // PRODUCT => CATEGORY => ID, NAME
    private int categoryId;

    // categoryId => "" ""
    // categoryName => product.category.name = ""
    // categoryType => product.category.type = ""
    private double product_rating;
}

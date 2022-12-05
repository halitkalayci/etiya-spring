package com.etiya.ecommercedemo.business.dtos.request.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class AddCategoryRequest {
    private String name;
    private String type;
}

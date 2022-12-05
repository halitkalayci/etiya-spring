package com.etiya.ecommercedemo.business.dtos.response.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryResponse {
    private int id;
    private String name;
    private String type;
}

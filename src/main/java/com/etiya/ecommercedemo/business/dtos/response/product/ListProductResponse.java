package com.etiya.ecommercedemo.business.dtos.response.product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ListProductResponse {
    // AllArgsConstructor için => oluşan ctor her zaman propertylerin sırasında oluşacaktır.
    private int id;
    private String name;

}

package com.etiya.ecommercedemo.entities.concretes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="unit_price")
    private double unit_price;

    @Column(name="stock")
    private int stock;

    @Column(name="product_rating")
    private double product_rating;

    @ManyToOne
    @JoinColumn(name="category_id")
    @JsonIgnoreProperties("products")
    private Category category;
}

// Http Request - Http Response araştırınız ve ikisi için de ayrı iki medium yazısı oluşturunuz. => Kişisel ÖDEV

// HTTP REQUEST => HTTP Request Anatomy, Request Types (GET,POST,PUT,DELETE)
// HTTP RESPONSE => Response status code
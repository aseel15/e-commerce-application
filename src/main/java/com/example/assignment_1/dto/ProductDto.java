package com.example.assignment_1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductDto {
    private Long id;

    private  String name;

    private double price;

    private int size;

    private int soldProduct;

    private int remainedProduct;

    private String color;

}
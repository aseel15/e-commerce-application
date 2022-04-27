package com.example.assignment_1.service;

import com.example.assignment_1.dto.ProductDto;

import java.util.List;

public interface ProductService {
    public List<ProductDto> getAllProducts();

    public ProductDto getProductById(Long id);

    public ProductDto addProduct(ProductDto productDto);

    public ProductDto updateProduct(ProductDto productDto, Long id);

    public void deleteProduct(Long id);
}

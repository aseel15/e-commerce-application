package com.example.assignment_1.controller;

import com.example.assignment_1.dto.ProductDto;
import com.example.assignment_1.exception.BadRequestException;
import com.example.assignment_1.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("/api/ecommerce/products")
public class ProductResource {
    @Autowired
    ProductService productService;

    public ProductResource(ProductService productService){
        this.productService = productService;
    }
    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        return ResponseEntity.ok().body(productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(productService.getProductById(id));
    }

    @PostMapping("/addProduct")
    public ResponseEntity<ProductDto> addProduct(@Valid @RequestBody ProductDto productDto){
        if (productDto.getId() != null) {
           // log.error("Cannot have an ID {}", productDto);
            throw new BadRequestException(ProductResource.class.getSimpleName(),
                    "Id");
        }
        return ResponseEntity.ok().body(productService.addProduct(productDto));
    }

    @PutMapping("updateProduct/{id}")
    public ResponseEntity<ProductDto> updateProduct(@Valid @RequestBody ProductDto productDto, @PathVariable(name = "id") Long id){
        return ResponseEntity.ok().body(productService.updateProduct(productDto, id));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable(name = "id") Long id){
        productService.deleteProduct(id);
        return new ResponseEntity<>("Deleted successfully.", HttpStatus.OK);
    }
}

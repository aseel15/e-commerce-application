package com.example.assignment_1.service.serviceImp;

import com.example.assignment_1.dto.ProductDto;
import com.example.assignment_1.entity.Product;
import com.example.assignment_1.exception.ResourceNotFoundException;
import com.example.assignment_1.repository.ProductRepository;
import com.example.assignment_1.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProductServiceImp implements ProductService {
    ProductRepository productRepository;

    public ProductServiceImp(ProductRepository productRepository){this.productRepository = productRepository;}

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(product -> mapToDto(product)).collect(Collectors.toList());
    }

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ProductServiceImp.class.getName(), "id", id));
        return mapToDto(product);
    }

    @Override
    public ProductDto addProduct(ProductDto productDto) {
        Product product = mapToEntity(productDto);
        Product newProduct = productRepository.save(product);
        return mapToDto(newProduct);
    }

    @Override
    public ProductDto updateProduct(ProductDto productDto, Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ProductServiceImp.class.getName(),"id",id));
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setSize(productDto.getSize());
        product.setSoldProduct(productDto.getSoldProduct());
        product.setRemainedProduct(productDto.getRemainedProduct());
        product.setColor(productDto.getColor());

        Product updatedProduct = productRepository.save(product);
        ProductDto updatedProductDto = mapToDto(updatedProduct);
        return updatedProductDto;
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(ProductServiceImp.class.getName(), "id", id));
        productRepository.delete(product);
    }

    private Product mapToEntity(ProductDto productDto){
        Product product = new Product();
        product.setId(productDto.getId());
        product.setName(productDto.getName());
        product.setPrice(productDto.getPrice());
        product.setSize(productDto.getSize());
        product.setSoldProduct(productDto.getSoldProduct());
        product.setRemainedProduct(productDto.getRemainedProduct());
        product.setColor(productDto.getColor());
        return product;
    }
    private ProductDto mapToDto(Product product){
        ProductDto productDto = new ProductDto(product.getId(), product.getName(), product.getPrice(), product.getSize(), product.getSoldProduct(), product.getRemainedProduct(), product.getColor());
        return productDto;

    }
}

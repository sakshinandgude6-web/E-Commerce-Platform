package com.sakshi.ecommerce.service.impl;

import com.sakshi.ecommerce.dto.ProductRequest;
import com.sakshi.ecommerce.dto.ProductResponse;
import com.sakshi.ecommerce.repository.ProductRepository;
import com.sakshi.ecommerce.service.ProductService;
import com.sakshi.ecommerce.entity.Product;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public ProductResponse createProduct(ProductRequest request) {

        Product product = productRepository.findByName(request.getName())
                .orElse(null);

        if (product != null) {

            // Product already exists → increase stock
            product.setStock(product.getStock() + request.getStock());

            Product updatedProduct = productRepository.save(product);

            return mapToResponse(updatedProduct);

        } else {

            // Create new product
            Product newProduct = Product.builder()
                    .name(request.getName())
                    .description(request.getDescription())
                    .price(request.getPrice())
                    .stock(request.getStock())
                    .build();

            Product savedProduct = productRepository.save(newProduct);

            return mapToResponse(savedProduct);
        }
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(this::mapToResponse)
                .toList();
    }

    private ProductResponse mapToResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .createdAt(product.getCreatedAt())
                .build();
    }
}

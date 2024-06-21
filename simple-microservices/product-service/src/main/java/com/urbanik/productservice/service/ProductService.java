package com.urbanik.productservice.service;

import com.urbanik.productservice.model.dto.ProductRequestDTO;
import com.urbanik.productservice.model.dto.ProductResponseDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    void createProduct(ProductRequestDTO product);

    List<ProductResponseDTO> getAllProducts();
}

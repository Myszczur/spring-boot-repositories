package com.urbanik.service;

import com.urbanik.model.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAllProducts();

    void addProduct(Product product);

    void deleteProduct(Long productId);

    void updateStock(Long productId, int quantity);
}

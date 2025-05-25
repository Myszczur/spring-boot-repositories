package com.urbanik.service.impl;

import com.urbanik.dao.ProductDAO;
import com.urbanik.model.Product;
import com.urbanik.service.ProductService;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductDAO productDAO;

    @Override
    public List<Product> getAllProducts() {
        try {
            return productDAO.getAllProducts();
        } catch (SQLException | IOException e) {
            return List.of();
        }
    }

    @Override
    public void addProduct(Product product) {
        try {
            productDAO.addProduct(product);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteProduct(Long productId) {
        try {
            productDAO.deleteProduct(productId);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStock(Long productId, int quantity) {
        try {
            productDAO.updateStock(productId, quantity);
        } catch (SQLException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

package com.urbanik.dao;

import com.urbanik.model.Product;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface ProductDAO {

    void addProduct(Product product) throws SQLException, IOException;

    List<Product> getAllProducts() throws SQLException, IOException;

    void updateStock(Long productId, int quantity) throws SQLException, IOException;

    void deleteProduct(Long productId) throws SQLException, IOException;
}

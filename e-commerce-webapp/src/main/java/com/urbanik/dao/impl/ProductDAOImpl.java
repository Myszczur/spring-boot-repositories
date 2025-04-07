package com.urbanik.dao.impl;

import com.urbanik.dao.ProductDAO;
import com.urbanik.model.Product;
import jakarta.annotation.Resource;
import jakarta.enterprise.context.ApplicationScoped;

import javax.sql.DataSource;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author kurbanik
 */

@ApplicationScoped
public class ProductDAOImpl implements ProductDAO {

    @Resource(lookup = "java:comp/env/jdbc/MyDS")
    private DataSource dataSource;

    private Connection getConnection() throws SQLException {
        return dataSource.getConnection();
    }


//    private Connection getConnection() throws SQLException, IOException {
//        DbConnection conn = new DbConnection();
//        return conn.getConnection();
//    }

    private void disconnect(Connection connection) throws SQLException {
        if (Objects.nonNull(connection) && !connection.isClosed()) {
            connection.close();
        }
    }

    @Override
    public void addProduct(Product product) throws SQLException, IOException {
        var sql = "INSERT INTO products (name, description, price, category, stock) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setDouble(2, product.getPrice());
            statement.setString(3, product.getDescription());
            statement.setString(4, product.getCategory());
            statement.setInt(5, product.getStock());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

//    private void checkConnection(Connection conn) throws SQLException {
//        if (Objects.isNull(conn)) {
//            throw new SQLException("Connection to database is null!");
//        }
//    }

    @Override
    public List<Product> getAllProducts() throws SQLException, IOException {
        var products = new ArrayList<Product>();
        var sql = "SELECT * FROM products";

        try (Connection conn = getConnection();
             Statement statement = conn.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                products.add(buildProduct(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
        return products;
    }

    private Product buildProduct(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getLong("id"))
                .name(resultSet.getString("name"))
                .price(resultSet.getDouble("price"))
                .description(resultSet.getString("description"))
                .category(resultSet.getString("category"))
                .stock(resultSet.getInt("stock"))
                .build();
    }

    @Override
    public void updateStock(Long productId, int quantity) throws SQLException, IOException {
        var sql = "UPDATE products SET stock = stock - ? WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, productId);
            statement.setInt(2, quantity);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }

    @Override
    public void deleteProduct(Long productId) throws SQLException, IOException {
        var sql = "DELETE FROM products WHERE id = ?";

        try (Connection conn = getConnection();
             PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setLong(1, productId);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getLocalizedMessage());
        }
    }
}

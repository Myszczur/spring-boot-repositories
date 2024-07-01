package com.urbanik.productservice.repository;

import com.urbanik.productservice.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author kurbanik
 */

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

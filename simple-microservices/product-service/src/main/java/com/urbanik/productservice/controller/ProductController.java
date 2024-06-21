package com.urbanik.productservice.controller;

import com.urbanik.productservice.model.Product;
import com.urbanik.productservice.model.dto.ProductRequestDTO;
import com.urbanik.productservice.model.dto.ProductResponseDTO;
import com.urbanik.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author kurbanik
 */

@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(@RequestBody ProductRequestDTO product) {

        log.info("Product Created! {}", product.toString());
        productService.createProduct(product);
    }

    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllAdvancedProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        if (products.isEmpty()) {
            log.info("No products!");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        log.info(products);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}

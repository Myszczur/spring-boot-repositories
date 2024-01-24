package pl.urbanik.productmanagementback.service;

import org.springframework.stereotype.Service;
import pl.urbanik.productmanagementback.model.Product;

import java.util.List;

@Service
public interface ProductService {
    Product saveProduct(Product product);

    List<Product> getAllProducts();

    Product getProductById(Long id);

    String deleteProductById(Long id);
    Product updateProductById(Product product, Long id);
}

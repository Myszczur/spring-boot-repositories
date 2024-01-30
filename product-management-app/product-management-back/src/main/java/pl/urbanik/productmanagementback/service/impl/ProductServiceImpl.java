package pl.urbanik.productmanagementback.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.urbanik.productmanagementback.exception.ResourceNotFoundException;
import pl.urbanik.productmanagementback.model.Product;
import pl.urbanik.productmanagementback.repository.ProductRepository;
import pl.urbanik.productmanagementback.service.ProductService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id).orElseThrow(() ->
                (new ResourceNotFoundException("Product not Found!")));
    }

    @Override
    public String deleteProductById(Long id) {
        Product product = getProductById(id);

        if (product != null) {
            productRepository.delete(product);
            return "Product Deleted Successfully!";
        } else {
            return "Product not DELETED!";
        }
    }

    @Override
    public Product updateProductById(Product product, Long id) {
        Product productToUpdate = getProductById(id);

        productToUpdate.setProductName(product.getProductName());
        productToUpdate.setDescription(product.getDescription());
        productToUpdate.setStatus(product.getStatus());
        productToUpdate.setPrice(product.getPrice());

        return productRepository.save(productToUpdate);
    }
}

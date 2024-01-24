package pl.urbanik.productmanagementback.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.urbanik.productmanagementback.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}

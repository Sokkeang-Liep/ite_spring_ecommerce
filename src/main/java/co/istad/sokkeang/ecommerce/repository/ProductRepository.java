package co.istad.sokkeang.ecommerce.repository;

import co.istad.sokkeang.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
                                          //Entity or Table Name, DataType of PK
public interface ProductRepository extends JpaRepository<Product, Integer> {
}

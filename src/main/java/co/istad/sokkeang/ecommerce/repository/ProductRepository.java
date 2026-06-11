package co.istad.sokkeang.ecommerce.repository;

import co.istad.sokkeang.ecommerce.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

/// Test with jpa Specification)

                             //Entity or Table Name, DataType of PK
@Repository
public interface ProductRepository extends  JpaRepository<Product, Integer>,
        JpaSpecificationExecutor<Product> {
}

package co.istad.sokkeang.ecommerce.repository;

import co.istad.sokkeang.ecommerce.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository //optional for JpaRepository
                                             //Entity or Table Name, DataType of PK
public interface CategoryRepository extends JpaRepository<Category, Integer>{

    boolean existsByName(String name); //check category

}

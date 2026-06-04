package co.istad.sokkeang.ecommerce.repository;

import co.istad.sokkeang.ecommerce.domain.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;
                                              //Entity or Table Name, DataType of PK
public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
}

package co.istad.sokkeang.ecommerce.features.order;
import co.istad.sokkeang.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.sokkeang.ecommerce.features.order.dto.OrderResponse;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface OrderService {

    OrderResponse createNew(CreateOrderRequest createOrderRequest);

    Page<OrderResponse> findAll(int  pageNumber, int pageSize);

    OrderResponse getById(UUID id);

    void hardDeleteById(UUID id);

    void softDeleteById(UUID id);

    void setPaymentStatus(UUID id);

}

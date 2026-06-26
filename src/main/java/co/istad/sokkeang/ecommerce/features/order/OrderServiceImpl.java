package co.istad.sokkeang.ecommerce.features.order;

import co.istad.sokkeang.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.sokkeang.ecommerce.features.order.dto.OrderResponse;
import co.istad.sokkeang.ecommerce.features.product.Product;
import co.istad.sokkeang.ecommerce.features.product.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderMapper orderMapper;

    @Override
    public OrderResponse createNew(CreateOrderRequest createOrderRequest) {
        List<OrderLine> orderLines = new ArrayList<>();
        final Order order = orderMapper.mapCreateOrderRequestToOrder(createOrderRequest);
        boolean isValidTrue = createOrderRequest.orderLines().stream()
                .allMatch(orderLineDto -> {
                    Optional<Product> optionalProduct = productRepository.findByCode(orderLineDto.code());
                    if(optionalProduct.isPresent()){
                        OrderLine orderLine = new OrderLine();
                        orderLine.setProduct(optionalProduct.get());
                        orderLine.setQty(optionalProduct.get().getQty());
                        orderLine.setOrder(order);
                        orderLine.setUnitPrice(optionalProduct.get().getUnitPrice());
                        orderLines.add(orderLine);
                        return true;
                    }
                    return false;
                });
        if(!isValidTrue){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Invalid order line.");
        }
        order.setCustomerId("ISTAD");
        order.setOrderLines(orderLines);
        order.setIsDeleted(false);
        order.setOrderedAt(LocalDateTime.now());
        order.setStatus(false);
        Order savedOrder = orderRepository.save(order);
        return orderMapper.mapOrderToOrderResponse(savedOrder);
    }

    @Override
    public Page<OrderResponse> findAll(int pageNumber, int pageSize) {
        Sort sortById = Sort.by(Sort.Direction.DESC, "id");
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,sortById);

        //retrieve record
        Page<Order> orders = orderRepository.findAll(pageRequest);

        return orders.map(orderMapper::mapOrderToOrderResponse);
    }

    @Override
    public OrderResponse getById(UUID id) {
        return orderRepository.findById(id)
                .map(orderMapper::mapOrderToOrderResponse).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Order has not been found with this id."));
    }

    @Override
    public void hardDeleteById(UUID id) {
        orderRepository.delete(
                orderRepository.findById(id)
                        .orElseThrow(()->new ResponseStatusException(
                                HttpStatus.NOT_FOUND,"Order ID has not been found"
                        )));
    }

    @Override
    public void softDeleteById(UUID id) {
        Order order = orderRepository.findById(id).
                orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Order ID has not been found"));

        order.setIsDeleted(true);

        orderRepository.save(order);
    }

    @Override
    public void setPaymentStatus(UUID id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Order ID has not been found."));
        orderRepository.save(order);
    }
}

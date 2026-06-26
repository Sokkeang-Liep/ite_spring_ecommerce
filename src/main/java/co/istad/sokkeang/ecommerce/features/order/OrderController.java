package co.istad.sokkeang.ecommerce.features.order;

import co.istad.sokkeang.ecommerce.features.order.dto.CreateOrderRequest;
import co.istad.sokkeang.ecommerce.features.order.dto.OrderResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@Slf4j

public class OrderController {

    private final OrderService orderService;
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse createNew(@RequestBody CreateOrderRequest createOrderRequest){
        return orderService.createNew(createOrderRequest);
    }

    @GetMapping
    public Page<OrderResponse> findAll(
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "25") int pageSize
    ){
        return orderService.findAll(pageNumber, pageSize);
    }

    @GetMapping("/{id}")
    public OrderResponse getById(@PathVariable UUID id){
        return orderService.getById(id);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable UUID id){
        log.info("deleteOrderById: {}", id);
        orderService.hardDeleteById(id);
    }

    @PutMapping("/{id}/soft-delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteById(@PathVariable UUID id){
        orderService.softDeleteById(id);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void setPaymentStatus(
            @PathVariable UUID id
    ){
        orderService.setPaymentStatus(id);
    }


}

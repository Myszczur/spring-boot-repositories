package com.urbanik.orderservice.service.impl;

import com.urbanik.orderservice.model.Order;
import com.urbanik.orderservice.model.OrderLineItems;
import com.urbanik.orderservice.model.dto.InventoryResponseDTO;
import com.urbanik.orderservice.model.dto.OrderLineItemsDTO;
import com.urbanik.orderservice.model.dto.OrderRequestDTO;
import com.urbanik.orderservice.repository.OrderRepository;
import com.urbanik.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * @author kurbanik
 */

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final WebClient.Builder webClient;


    @Override
    public String placeOrder(OrderRequestDTO orderRequestDTO) {

        Order order = Order.builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItems(orderRequestDTO.getOrderLineItems()
                        .stream()
                        .map(this::mapToDto)
                        .toList())
                .build();

        List<String> skuCode = order.getOrderLineItems()
                .stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryResponseDTO[] result = webClient.build().get()
                .uri("http://localhost:8082/api/v1/invenory", uriBuilder ->
                        uriBuilder.queryParam("skuCode", skuCode)
                                .build())
                .retrieve()
                .bodyToMono(InventoryResponseDTO[].class)
                .block();

        assert result != null;
        boolean allProductInStock = Arrays.stream(result)
                .allMatch(InventoryResponseDTO::isInStock);

        if (allProductInStock) {
            log.info("Order placed successfully: {}", order);
            orderRepository.save(order);

            return "Order Placed Successfully! :)";
        } else {
            log.error("Product is not in stock: {}", (Object) result);
            throw new IllegalArgumentException("Product is not in stock!");
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDTO orderLine) {

        return OrderLineItems.builder()
                .id(orderLine.getId())
                .skuCode(orderLine.getSkuCode())
                .price(orderLine.getPrice())
                .quantity(orderLine.getQuantity())
                .build();
    }
}

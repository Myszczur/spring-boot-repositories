package com.urbanik.orderservice.controller;

import com.urbanik.orderservice.model.dto.OrderRequestDTO;
import com.urbanik.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author kurbanik
 */

@Log4j2
@RestController
@CrossOrigin("*")
@RequiredArgsConstructor
@RequestMapping("/api/v1/order")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequestDTO order) {

        orderService.placeOrder(order);
    }
}

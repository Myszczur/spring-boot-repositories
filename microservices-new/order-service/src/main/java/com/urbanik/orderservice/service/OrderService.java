package com.urbanik.orderservice.service;


import com.urbanik.orderservice.model.dto.OrderRequestDTO;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    void placeOrder(OrderRequestDTO order);
}

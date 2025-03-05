package com.urbanik.model;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author kurbanik
 */

@Data
@Builder
@ToString
@AllArgsConstructor
public class Order {

    private Long id;
    private Long userId;
    private LocalDate orderDate;
    private String status;
    private List<OrderItem> items;
}

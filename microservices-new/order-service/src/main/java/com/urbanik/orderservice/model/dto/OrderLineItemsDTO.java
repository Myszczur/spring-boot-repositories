package com.urbanik.orderservice.model.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author kurbanik
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderLineItemsDTO {

    private Long id;
    private String skuCode;
    private BigDecimal price;
    private Integer quantity;
}

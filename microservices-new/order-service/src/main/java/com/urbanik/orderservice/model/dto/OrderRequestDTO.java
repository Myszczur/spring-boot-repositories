package com.urbanik.orderservice.model.dto;

import com.urbanik.orderservice.model.OrderLineItems;
import lombok.*;

import java.util.List;

/**
 * @author kurbanik
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

    private List<OrderLineItemsDTO> orderLineItems;
}

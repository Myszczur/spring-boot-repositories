package com.urbanik.inventoryservice.model.dto;

import lombok.*;

/**
 * @author kurbanik
 */

@Setter
@Getter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponseDTO {

    private String skuCode;
    private boolean isInStock;
}

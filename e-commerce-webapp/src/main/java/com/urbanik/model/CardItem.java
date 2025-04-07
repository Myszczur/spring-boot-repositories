package com.urbanik.model;

import lombok.*;

/**
 * @author kurbanik
 */

@Data
@Builder
@ToString
@AllArgsConstructor
public class CardItem {

    private Long id;
    private Long userId;
    private Long productId;
    private int quantity;
}

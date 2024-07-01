package com.urbanik.productservice.model.dto;

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
public class ProductRequestDTO {

    private String name;
    private String description;
    private BigDecimal price;
}

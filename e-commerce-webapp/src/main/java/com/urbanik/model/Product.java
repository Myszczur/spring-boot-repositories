package com.urbanik.model;

import lombok.*;

/**
 * @author kurbanik
 */

@Data
@Builder
@ToString
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int stock;
}

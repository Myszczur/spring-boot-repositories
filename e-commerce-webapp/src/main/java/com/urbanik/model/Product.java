package com.urbanik.model;

import lombok.*;

/**
 * @author kurbanik
 */

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    private Long id;
    private String name;
    private double price;
    private String description;
    private String category;
    private int stock;
}

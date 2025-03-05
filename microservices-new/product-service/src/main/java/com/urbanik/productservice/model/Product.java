package com.urbanik.productservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/**
 * @author kurbanik
 */

@Entity
@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String name;
    private String description;
    private BigDecimal price;

}

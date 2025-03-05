package com.urbanik.inventoryservice.model;

import jakarta.persistence.*;
import lombok.*;

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
@Table(name = "t_inventory")
public class Inventory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String skuCode;
    private Integer quantity;
}

package com.urbanik.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
@Table(name = "t_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    private String orderNumber;

    @OneToMany(cascade = CascadeType.ALL)
    @ToString.Exclude
    private List<OrderLineItems> orderLineItems;
}

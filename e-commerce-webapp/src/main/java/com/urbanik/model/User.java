package com.urbanik.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * @author kurbanik
 */

@Data
@Builder
@ToString
@AllArgsConstructor
public class User {

    private Long id;
    private String username;
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private String role;
    private List<Order> orders;
}

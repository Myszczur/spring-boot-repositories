package com.urbanik.authenticatedloginsystem.model.dto;

import lombok.*;

/**
 * @author kurbanik
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationDTO {

    private String username;
    private String password;
}

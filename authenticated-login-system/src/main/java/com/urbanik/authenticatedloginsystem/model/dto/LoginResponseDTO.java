package com.urbanik.authenticatedloginsystem.model.dto;

import com.urbanik.authenticatedloginsystem.model.User;
import lombok.*;

/**
 * @author kurbanik
 */

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private User user;
    private String jwt;
}

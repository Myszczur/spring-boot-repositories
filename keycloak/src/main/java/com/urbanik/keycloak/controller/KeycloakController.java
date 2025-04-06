package com.urbanik.keycloak.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kurbanik
 */

@RestController
@RequestMapping("/api/vi/keycloak")
public class KeycloakController {

    @GetMapping
    @PreAuthorize("hasRole('client_user')")
    public String hello() {

        return "Hello from Controller";
    }

    @GetMapping("/hello-2")
    @PreAuthorize("hasRole('client_admin')")
    public String helloTwo() {

        return "Hello from Controller - ADMIN";
    }
}

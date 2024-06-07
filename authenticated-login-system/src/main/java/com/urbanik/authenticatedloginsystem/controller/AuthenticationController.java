package com.urbanik.authenticatedloginsystem.controller;

import com.urbanik.authenticatedloginsystem.model.User;
import com.urbanik.authenticatedloginsystem.model.dto.LoginResponseDTO;
import com.urbanik.authenticatedloginsystem.model.dto.RegistrationDTO;
import com.urbanik.authenticatedloginsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;

/**
 * @author kurbanik
 */

@Log4j2
@RestController
@CrossOrigin("*")
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUSer(@RequestBody RegistrationDTO registrationDTO) throws RoleNotFoundException {

        log.info(registrationDTO.toString());
        return authenticationService.registerUser(registrationDTO.getUsername(), registrationDTO.getPassword());
    }

    @PostMapping("/login")
    public LoginResponseDTO loginUser(@RequestBody RegistrationDTO user) {

        log.info("Login User: {}", user.getUsername());
        return authenticationService.loginUSer(user.getUsername(), user.getPassword());
    }
}

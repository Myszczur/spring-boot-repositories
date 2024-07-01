package com.urbanik.authenticatedloginsystem.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kurbanik
 */


@Log4j2
@RestController
@RequestMapping("/user")
@CrossOrigin("*")
public class UserController {


    @GetMapping("/")
    public String testUserController() {

        log.info("User access level");
        return "User access level";
    }
}

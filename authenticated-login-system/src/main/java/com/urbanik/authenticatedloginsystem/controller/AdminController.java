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
@RequestMapping("/admin")
@CrossOrigin("*")
public class AdminController {


    @GetMapping("/")
    public String testAdminController() {

        log.info("Admin level access");
        return "Admin level access";
    }
}

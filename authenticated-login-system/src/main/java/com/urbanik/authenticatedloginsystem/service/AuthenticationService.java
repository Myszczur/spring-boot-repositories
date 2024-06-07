package com.urbanik.authenticatedloginsystem.service;

import com.urbanik.authenticatedloginsystem.model.User;
import com.urbanik.authenticatedloginsystem.model.dto.LoginResponseDTO;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;

@Service
public interface AuthenticationService {

    User registerUser(String username, String password) throws RoleNotFoundException;

    LoginResponseDTO loginUSer(String username, String password);
}

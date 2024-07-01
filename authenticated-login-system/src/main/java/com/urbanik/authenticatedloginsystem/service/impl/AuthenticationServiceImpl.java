package com.urbanik.authenticatedloginsystem.service.impl;

import com.urbanik.authenticatedloginsystem.model.Role;
import com.urbanik.authenticatedloginsystem.model.RolesEnum;
import com.urbanik.authenticatedloginsystem.model.User;
import com.urbanik.authenticatedloginsystem.model.dto.LoginResponseDTO;
import com.urbanik.authenticatedloginsystem.repository.RoleRepository;
import com.urbanik.authenticatedloginsystem.repository.UserRepository;
import com.urbanik.authenticatedloginsystem.service.AuthenticationService;
import com.urbanik.authenticatedloginsystem.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

/**
 * @author kurbanik
 */

@Log4j2
@Service
@Transactional
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @Override
    public User registerUser(String username, String password) throws RoleNotFoundException {

        String encodedPassword = passwordEncoder.encode(password);

        Optional<Role> optionalUserRole = roleRepository.findByAuthority(String.valueOf(RolesEnum.USER));
        Role userRole;
        Set<Role> authorities;

        if (optionalUserRole.isPresent()) {
            userRole = optionalUserRole.get();
            authorities = new HashSet<>();
            authorities.add(userRole);
        } else {
            throw new RoleNotFoundException("User Role is Not valid!");
        }

        return userRepository.save(new User(0L, username, encodedPassword, authorities));
    }

    @Override
    public LoginResponseDTO loginUSer(String username, String password) {

        try {
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(auth);
            Optional<User> foundUser = userRepository.findByUsername(username);

            User user;
            if (foundUser.isPresent()) {
                user = foundUser.get();
            } else {
                throw new UsernameNotFoundException("User is not Valid!");
            }

            return new LoginResponseDTO(user, token);
        } catch (AuthenticationException ex) {
            return new LoginResponseDTO(null, "");
        }
    }
}

package com.urbanik.authenticatedloginsystem;

import com.urbanik.authenticatedloginsystem.model.Role;
import com.urbanik.authenticatedloginsystem.model.RolesEnum;
import com.urbanik.authenticatedloginsystem.model.User;
import com.urbanik.authenticatedloginsystem.repository.RoleRepository;
import com.urbanik.authenticatedloginsystem.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class AuthenticatedLoginSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthenticatedLoginSystemApplication.class, args);
    }

    @Bean
    CommandLineRunner run(RoleRepository roleRepository,
                          UserRepository userRepository,
                          PasswordEncoder passwordEncoder) {
        return args -> {

            if (roleRepository.findByAuthority(String.valueOf(RolesEnum.ADMIN)).isPresent()) {
                return;
            }

            Role adminRole = roleRepository.save(new Role(1L, String.valueOf(RolesEnum.ADMIN)));
            roleRepository.save(new Role(2L, String.valueOf(RolesEnum.USER)));

            Set<Role> roleSet = new HashSet<>();
            roleSet.add(adminRole);
            userRepository.save(new User(1L,
                    "admin",
                    passwordEncoder.encode("password"),
                    roleSet));
        };
    }
}

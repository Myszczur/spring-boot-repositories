package com.urbanik.authenticatedloginsystem.service.impl;

import com.urbanik.authenticatedloginsystem.service.TokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

/**
 * @author kurbanik
 */

@Log4j2
@Service
@RequiredArgsConstructor
public class TokenServiceImpl implements TokenService {

    @Autowired
    private JwtEncoder jwtEncoder;

    @Autowired
    private JwtDecoder jwtDecoder;


    @Override
    public String generateJwt(Authentication auth) {

        Instant now = Instant.now();

        String scope = auth.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));

        JwtClaimsSet claimsSet = JwtClaimsSet
                .builder()
                .issuer("self")
                .issuedAt(now)
                .subject(auth.getName())
                .claim("roles", scope)
                .build();

        return jwtEncoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
    }
}

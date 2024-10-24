package com.grupo09.generation.security;

import com.grupo09.generation.dto.out.LoginOutput;
import com.grupo09.generation.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtGenerate{
    private final JwtEncoder encoder;

    public JwtGenerate(JwtEncoder encoder){
        this.encoder = encoder;
    }

    @Value("${jwt.expiration}")
    private String jwtExpiration;

    public LoginOutput token(EmployeeModel employeeModel){
        Instant now = Instant.now();
        long expiresIn = Long.parseLong(jwtExpiration);
        JwtClaimsSet claimsSet = JwtClaimsSet.builder()
                .issuer("generation")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .subject(employeeModel.getId().toString())
                .build();
        String accessToken = encoder.encode(JwtEncoderParameters.from(claimsSet)).getTokenValue();
        return new LoginOutput(accessToken, expiresIn);
    }
}

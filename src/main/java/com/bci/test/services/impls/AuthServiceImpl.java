package com.bci.test.services.impls;

import com.bci.test.models.api.AuthRequest;
import com.bci.test.models.api.AuthResponse;
import com.bci.test.services.AuthService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    @Override
    public AuthResponse login(AuthRequest request) {
        String token = getJWTToken(request.getEmail());
        AuthResponse response = new AuthResponse(request.getEmail(), token);
        return response;
    }

    private String getJWTToken(String username) {
        String secretKey = "123456";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ADMIN");

        String token = Jwts
                .builder()
                .setId("BCI")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}

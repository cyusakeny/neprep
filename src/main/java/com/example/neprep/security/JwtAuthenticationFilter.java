package com.example.neprep.security;
import com.auth0.jwt.JWT;
import com.example.neprep.dtos.SigninDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;

@Getter
@Setter
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    @Value("${jwt.secret}")
    private String jwtSecret;
    private AuthenticationManager authenticationManager;
    @Value("${jwt.expiresIn}")
    private int jwtExpirationInMs;
    public JwtAuthenticationFilter(AuthenticationManager authenticationManager){
        this.authenticationManager = authenticationManager;

    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        SigninDto loginData = null;
        try {
            loginData = new ObjectMapper().readValue(request.getInputStream(), SigninDto.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Create login token
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                loginData.getUsername(),
                loginData.getPassword(),
                new ArrayList<>());

        // Authenticate user
        Authentication auth = authenticationManager.authenticate(authenticationToken);

        return auth;
    }
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        // Grab principal
        UserPrincipal principal = (UserPrincipal) authResult.getPrincipal();

        // Create JWT Token
        String token = JWT.create()
                .withSubject(principal.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + jwtExpirationInMs))
                .sign(HMAC512(jwtSecret.getBytes()));
        // Add token in response
        response.addHeader("Authorization", "Bearer " +  token);
    }
}


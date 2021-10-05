package com.codecool.sharendrivebackend.controller;


import com.codecool.sharendrivebackend.dao.CustomerRepository;
import com.codecool.sharendrivebackend.model.customer.CustomerCredentials;
import com.codecool.sharendrivebackend.security.JwtTokenServices;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import com.codecool.sharendrivebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenServices jwtTokenServices;

    public static Logger logger = LoggerFactory.getLogger(AuthController.class);

    private final CustomerService customerService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, JwtTokenServices jwtTokenServices, CustomerRepository users, CustomerService customerService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenServices = jwtTokenServices;
        this.customerService = customerService;
    }

    @ResponseStatus(HttpStatus.OK)
    @PostMapping("/signin")
    public ResponseEntity signin(@RequestBody CustomerCredentials data) {
        try {
            String username = data.getUsername();
            logger.warn(username);
            String customerId = customerService.getCustomerIdByUsername(data.getUsername());
            // authenticationManager.authenticate calls loadUserByUsername in CustomUserDetailsService
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, data.getPassword()));
            List<String> roles = authentication.getAuthorities()
                    .stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toList());

            String token = jwtTokenServices.createToken(customerId, roles);

            Map<Object, Object> model = new HashMap<>();
            model.put("username", username);
            model.put("roles", roles);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Invalid username/password supplied");
        }
    }
}
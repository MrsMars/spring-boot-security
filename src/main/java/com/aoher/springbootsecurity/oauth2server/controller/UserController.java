package com.aoher.springbootsecurity.oauth2server.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/user")
    public Principal user(Principal user) {
        return user;
    }

    @GetMapping("/authentication")
    public Object getAuthentication(@CurrentSecurityContext(expression = "authentication") Authentication authentication) {
        log.info("authentication  0> {}", authentication);
        return authentication.getDetails();
    }

    @GetMapping("/principal")
    public String getPrincipal(@CurrentSecurityContext(expression = "authentication.principal") Principal principal) {
        log.info("principal -> {}", principal);
        return principal.getName();
    }
}

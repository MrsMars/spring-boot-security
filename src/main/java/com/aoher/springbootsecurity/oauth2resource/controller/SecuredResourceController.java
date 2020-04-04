package com.aoher.springbootsecurity.oauth2resource.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecuredResourceController {

    @GetMapping("/securedResource")
    public String securedResource() {
        return "Aoher Secured Resource OK";
    }
}

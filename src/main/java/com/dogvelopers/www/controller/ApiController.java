package com.dogvelopers.www.controller;

import com.dogvelopers.www.security.tokens.PostAuthorizationToken;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@PreAuthorize("hasRole('ROLE_USER')")
public class ApiController {

    @GetMapping("/user")
    public String getUsername(Authentication authentication){
        PostAuthorizationToken token = (PostAuthorizationToken) authentication;
        return token.getName();
    }
}

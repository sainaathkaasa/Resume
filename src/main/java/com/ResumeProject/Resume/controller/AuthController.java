package com.ResumeProject.Resume.controller;

import com.ResumeProject.Resume.config.JwtUtil;
import com.ResumeProject.Resume.entity.AuthRequest;
import com.ResumeProject.Resume.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    CustomUserDetailService userDetailsService;

    @Autowired
    JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public ResponseEntity<?> authenticate(@RequestBody AuthRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        UserDetails user = userDetailsService
                .loadUserByUsername(request.getUsername());

        String token = jwtUtil.generateToken(user);

        return ResponseEntity.ok(token);
    }
}

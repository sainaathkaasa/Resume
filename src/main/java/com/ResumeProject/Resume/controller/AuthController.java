package com.ResumeProject.Resume.controller;

import com.ResumeProject.Resume.config.JwtUtil;
import com.ResumeProject.Resume.entity.AuthRequest;
import com.ResumeProject.Resume.exception.ResourceNotFoundException;
import com.ResumeProject.Resume.exception.UnauthorizedException;
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

import java.util.HashMap;
import java.util.Map;

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

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
        }catch (Exception ex){
            throw new UnauthorizedException("Invalid username or password");
        }

        UserDetails user = userDetailsService
                .loadUserByUsername(request.getUsername());

        String token = jwtUtil.generateToken(user);

        Map<String, Object> response = new HashMap<>();
        response.put("message", "Successfully logged in");
        response.put("token", token);

        return ResponseEntity.ok(response);
    }
}

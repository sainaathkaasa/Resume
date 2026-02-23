package com.ResumeProject.Resume.controller;

import com.ResumeProject.Resume.DTO.ProfileRequest;
import com.ResumeProject.Resume.DTO.ProfileResponse;
import com.ResumeProject.Resume.DTO.SkillsRequest;
import com.ResumeProject.Resume.DTO.UserCreateRequest;
import com.ResumeProject.Resume.Mappers.ProfileMapper;
import com.ResumeProject.Resume.Mappers.SkillsMapper;
import com.ResumeProject.Resume.Mappers.UserMapper;
import com.ResumeProject.Resume.entity.*;
import com.ResumeProject.Resume.repository.*;
import com.ResumeProject.Resume.service.ServiceInterface.ProfileServiceInterface;
import com.ResumeProject.Resume.service.ServiceInterface.SkillServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.Region;
import java.util.List;
import java.util.Optional;

@RestController
public class DashboardController {

    //region DI
    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProfileServiceInterface profileServiceInterface;

    //endregion

    //region User Methods
    @PostMapping("/UserCreate")
    public ResponseEntity<?> createUser(@RequestBody UserCreateRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            return ResponseEntity
                    .badRequest()
                    .body("Email already exists");
        }

        Users user = userMapper.toEntity(request);
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        userRepository.save(user);

        return ResponseEntity.ok("User created successfully");
    }

    //endregion

    //region Profile Methods

    @PostMapping("/UpsertProfile")
    public ResponseEntity<?> saveProfile(@RequestBody ProfileRequest request) {

        return Optional.of(profileServiceInterface.UpsertProfile(request))
                .filter(Boolean::booleanValue)
                .map(r -> ResponseEntity.ok("Profile saved successfully"))
                .orElseGet(() -> ResponseEntity.internalServerError().body("Something went wrong"));
    }

    // USER + ADMIN: View profile
    @GetMapping("/GetProfile")
    public ResponseEntity<ProfileResponse> getProfile() {
        return ResponseEntity.ok(profileServiceInterface.GetProfile());
    }

    //endregion

}

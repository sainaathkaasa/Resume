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
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.swing.plaf.synth.Region;
import java.util.List;

@RestController
public class DashboardController {

    @Autowired
    private UserDetailsRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;
    @Autowired
    private SkillsMapper skillsMapper;

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private EducationRepository educationRepository;

    @Autowired
    private ProjectRepository projectRepository;


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

    @PostMapping("/UpsertProfile")
    public ResponseEntity<?> saveProfile(@RequestBody ProfileRequest request) {

        // Only one profile → update if exists
        Profile profile = profileRepository.findAll()
                .stream()
                .findFirst()
                .orElse(new Profile());

        Profile updated = profileMapper.toEntity(request);
        updated.setId(profile.getId()); // preserve ID for update

        profileRepository.save(updated);

        return ResponseEntity.ok("Profile saved successfully");
    }

    // USER + ADMIN: View profile
    @GetMapping("/GetProfile")
    public ResponseEntity<ProfileResponse> getProfile() {

        Profile profile = profileRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Profile not found"));

        return ResponseEntity.ok(profileMapper.toResponse(profile));
    }

    @PostMapping("/UpsertSkills")
    public ResponseEntity<?> saveSkills(@RequestBody List<SkillsRequest> requests) {
        List<Skills> skills = skillsMapper.toEntityList(requests);
        skillsRepository.saveAll(skills);
        return ResponseEntity.ok("Skills saved successfully");
    }
    @GetMapping("/Languages")
    public List<String> getLanguages() {
        return skillsRepository.findAllLanguages();
    }

    @GetMapping("/specificskill")
    public Skills getSpedificDkill(String language){
        return skillsRepository.findByLanguage(language);
    }

    @PutMapping("/updateskill")
    public Skills updateSpecificSkill(@RequestBody Skills updatedSkill)
    {
        Skills skill = skillsRepository.findByLanguage(updatedSkill.getLanguage());
        if(skill == null){
            return new Skills();
        }

        // update fields
        skill.setDescription(updatedSkill.getDescription());

        // save = update
        return skillsRepository.save(skill);
    }

    @DeleteMapping("/admin/deletespecificskill")
    @Transactional
    public void deleteSpedificDkill(@RequestParam String language){
        skillsRepository.deleteByLanguage(language);
    }

    @GetMapping("/GetEducation")
    public List<Education> getAllEducation() {
        return educationRepository.findAll();
    }

    @GetMapping("/GetProjects")
    public List<Project> getAllProjects() {
        return projectRepository.findAll();
    }


}

package com.ResumeProject.Resume.controller;

import com.ResumeProject.Resume.DTO.SkillsRequest;
import com.ResumeProject.Resume.entity.Skills;
import com.ResumeProject.Resume.service.ServiceInterface.SkillServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SkillsController {

    @Autowired
    private SkillServiceInterface skillServiceInterface;

    @GetMapping("/Languages")
    public List<String> getLanguages() {
        return skillServiceInterface.getLanguages();
    }

    @GetMapping("/SpecificSkill")
    public Skills getSpecificSkill(String language){
        return skillServiceInterface.getSpecificSkill(language);
    }

    @DeleteMapping("/admin/deleteSpecificSkill")
    public ResponseEntity<String> deleteSpecificSkill(@RequestParam String language){
        skillServiceInterface.deleteSpecificSkill(language);

        return ResponseEntity.ok("Skill" + language + "Deleted Successfully ");
    }

    @PutMapping("/updateSkill")
    public Skills updateSpecificSkill(@RequestBody Skills updatedSkill)
    {
        return  skillServiceInterface.UpdateSkills(updatedSkill);
    }

    @PostMapping("/UpsertSkills")
    public ResponseEntity<?> saveSkills(@RequestBody List<SkillsRequest> skills) {
        skillServiceInterface.SaveAllSkills(skills);
        return ResponseEntity.ok("Skills saved successfully");
    }
}

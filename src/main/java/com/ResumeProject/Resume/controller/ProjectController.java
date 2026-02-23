package com.ResumeProject.Resume.controller;

import com.ResumeProject.Resume.entity.Project;
import com.ResumeProject.Resume.service.ServiceInterface.ProjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProjectController {
    @Autowired
    private ProjectServiceInterface projectServiceInterface;

    @GetMapping("/GetProjects")
    public List<Project> getAllProjects(){
        return projectServiceInterface.GetAllProjects();
    }

    @PostMapping("/UpsertProject")
    public ResponseEntity<?> upsertProject(@RequestBody Project project){
        return Optional.of(projectServiceInterface.Upsertproject(project))
                .filter(Boolean::booleanValue)
                .map(r -> ResponseEntity.ok("Project Upserted Successfully"))
                .orElseGet(() -> ResponseEntity.internalServerError().body("Something went wrong"));
    }

    @DeleteMapping("/DeleteProjectById/{id}")
    public ResponseEntity<?> deleteProjectById(@PathVariable Long id){
        return Optional.of(projectServiceInterface.DeleteProjectbyid(id))
                .filter(Boolean::booleanValue)
                .map(r -> ResponseEntity.ok("Project Deleted Successfully"))
                .orElseGet(() -> ResponseEntity.internalServerError().body("Something went wrong"));
    }
}

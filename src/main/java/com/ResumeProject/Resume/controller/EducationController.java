package com.ResumeProject.Resume.controller;

import com.ResumeProject.Resume.entity.Education;
import com.ResumeProject.Resume.service.ServiceInterface.EducationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EducationController {

    @Autowired
    private EducationServiceInterface educationServiceInterface;
    @GetMapping("/GetEducation")
    public List<Education> GetAllEducation(){
        return educationServiceInterface.GetAllEducationdetails();
    }

}

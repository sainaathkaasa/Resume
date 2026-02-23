package com.ResumeProject.Resume.service.ImplimentationService;

import com.ResumeProject.Resume.entity.Education;
import com.ResumeProject.Resume.repository.EducationRepository;
import com.ResumeProject.Resume.service.ServiceInterface.EducationServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EducationServiceImplement implements EducationServiceInterface {

    @Autowired
    private EducationRepository educationRepository;
    @Override
    public List<Education> GetAllEducationdetails() {
        return educationRepository.findAll();
    }
}

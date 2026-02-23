package com.ResumeProject.Resume.service.ServiceInterface;

import com.ResumeProject.Resume.entity.Education;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EducationServiceInterface {

    List<Education> GetAllEducationdetails();
}

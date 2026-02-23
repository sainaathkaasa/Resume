package com.ResumeProject.Resume.service.ImplimentationService;

import com.ResumeProject.Resume.entity.Project;
import com.ResumeProject.Resume.repository.ProjectRepository;
import com.ResumeProject.Resume.service.ServiceInterface.ProjectServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serial;
import java.util.List;

@Service
public class ProjectServiceImplement implements ProjectServiceInterface {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> GetAllProjects() {
        return projectRepository.findAll();
    }

    @Override
    public boolean Upsertproject(Project project) {
        try {
            projectRepository.save(project);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    @Override
    public boolean DeleteProjectbyid(Long id) {
        try {
            projectRepository.deleteById(id);
            return true;
        } catch (Exception ex){
            return false;
        }
    }
}

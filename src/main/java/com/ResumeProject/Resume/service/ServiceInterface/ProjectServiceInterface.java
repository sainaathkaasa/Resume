package com.ResumeProject.Resume.service.ServiceInterface;

import com.ResumeProject.Resume.entity.Project;

import java.util.List;

public interface ProjectServiceInterface {
    List<Project> GetAllProjects();

    boolean Upsertproject(Project project);

    boolean DeleteProjectbyid(Long id);
}

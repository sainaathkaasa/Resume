package com.ResumeProject.Resume.repository;

import com.ResumeProject.Resume.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProjectRepository extends JpaRepository<Project, Long> {
}

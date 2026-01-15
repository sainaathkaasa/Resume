package com.ResumeProject.Resume.repository;

import com.ResumeProject.Resume.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfileRepository extends JpaRepository<Profile, Long> {
}

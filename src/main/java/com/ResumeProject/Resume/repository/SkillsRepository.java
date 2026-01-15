package com.ResumeProject.Resume.repository;

import com.ResumeProject.Resume.entity.Skills;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SkillsRepository extends JpaRepository<Skills, Long> {

    @Query("SELECT s.language FROM Skills s")
    List<String> findAllLanguages();

    Skills findByLanguage(String language);

    void deleteByLanguage(String language);

}

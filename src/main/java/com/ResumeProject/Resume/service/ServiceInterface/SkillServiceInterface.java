package com.ResumeProject.Resume.service.ServiceInterface;

import com.ResumeProject.Resume.DTO.SkillsRequest;
import com.ResumeProject.Resume.entity.Skills;

import java.util.List;

public interface SkillServiceInterface {

    List<String> getLanguages();

    Skills getSpecificSkill(String language);

    void deleteSpecificSkill(String language);

    Skills UpdateSkills(Skills updatedSkill);

    void SaveAllSkills(List<SkillsRequest> skills);

}

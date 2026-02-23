package com.ResumeProject.Resume.service.ImplimentationService;

import com.ResumeProject.Resume.DTO.SkillsRequest;
import com.ResumeProject.Resume.Mappers.SkillsMapper;
import com.ResumeProject.Resume.entity.Skills;
import com.ResumeProject.Resume.exception.ResourceNotFoundException;
import com.ResumeProject.Resume.repository.SkillsRepository;
import com.ResumeProject.Resume.service.ServiceInterface.SkillServiceInterface;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SkillServiceImplement implements SkillServiceInterface {

    @Autowired
    private SkillsRepository skillsRepository;

    @Autowired
    private SkillsMapper skillsMapper;

    @Override
    public List<String> getLanguages() {
        return skillsRepository.findAllLanguages();
    }

    @Override
    public Skills getSpecificSkill(String language) {
        return skillsRepository.findByLanguage(language);
    }

    @Override
    @Transactional
    public void deleteSpecificSkill(String language) {
        Skills skill = skillsRepository.findByLanguage(language);
        if (skill == null) {
            throw new ResourceNotFoundException(
                    "Skill not found with language: " + language);
        }
        skillsRepository.deleteByLanguage(language);
    }

    @Override
    public Skills UpdateSkills(Skills updatedSkill) {
        Skills skill = skillsRepository.findByLanguage(updatedSkill.getLanguage());
        if(skill == null){
            return new Skills();
        }
        // update fields
        skill.setDescription(updatedSkill.getDescription());
        // save = update
        return skillsRepository.save(skill);
    }

    @Override
    public void SaveAllSkills(List<SkillsRequest> skills) {
        List<Skills> skillSet = skillsMapper.toEntityList(skills);
        skillsRepository.saveAll(skillSet);
    }
}

package com.ResumeProject.Resume.Mappers;

import com.ResumeProject.Resume.DTO.SkillsRequest;
import com.ResumeProject.Resume.entity.Skills;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SkillsMapper {
    // Single
    Skills toEntity(SkillsRequest request);

    // 🔥 Bulk (THIS IS THE KEY)
    List<Skills> toEntityList(List<SkillsRequest> requests);
}

package com.ResumeProject.Resume.Mappers;

import com.ResumeProject.Resume.DTO.ProfileRequest;
import com.ResumeProject.Resume.DTO.ProfileResponse;
import com.ResumeProject.Resume.entity.Profile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProfileMapper {
    @Mapping(target = "id", ignore = true)
    Profile toEntity(ProfileRequest request);

    ProfileResponse toResponse(Profile profile);
}

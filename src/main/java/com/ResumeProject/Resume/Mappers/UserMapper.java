package com.ResumeProject.Resume.Mappers;

import com.ResumeProject.Resume.DTO.UserCreateRequest;
import com.ResumeProject.Resume.entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "role", constant = "ROLE_USER")
    @Mapping(target = "password", ignore = true) // encode manually
    Users toEntity(UserCreateRequest request);
}

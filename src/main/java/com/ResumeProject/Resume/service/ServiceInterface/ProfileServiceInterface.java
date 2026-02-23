package com.ResumeProject.Resume.service.ServiceInterface;

import com.ResumeProject.Resume.DTO.ProfileRequest;
import com.ResumeProject.Resume.DTO.ProfileResponse;
import com.ResumeProject.Resume.entity.Profile;

import java.util.List;

public interface ProfileServiceInterface {

    ProfileResponse GetProfile();

    boolean UpsertProfile(ProfileRequest profileRequest);


}

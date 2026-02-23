package com.ResumeProject.Resume.service.ImplimentationService;

import com.ResumeProject.Resume.DTO.ProfileRequest;
import com.ResumeProject.Resume.DTO.ProfileResponse;
import com.ResumeProject.Resume.Mappers.ProfileMapper;
import com.ResumeProject.Resume.entity.Profile;
import com.ResumeProject.Resume.repository.ProfileRepository;
import com.ResumeProject.Resume.service.ServiceInterface.ProfileServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileServiceImplement implements ProfileServiceInterface {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private ProfileMapper profileMapper;

    @Override
    public ProfileResponse GetProfile() {
        Profile profile = profileRepository.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Profile Not Found"));

        return profileMapper.toResponse(profile);

    }

    @Override
    public boolean UpsertProfile(ProfileRequest profileRequest) {
        try {
            Profile profile = profileRepository.findAll()
                    .stream()
                    .findFirst()
                    .orElse(new Profile());

            Profile updateprofile = profileMapper.toEntity(profileRequest);
            updateprofile.setId(profile.getId());

            profileRepository.save(updateprofile);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }
}

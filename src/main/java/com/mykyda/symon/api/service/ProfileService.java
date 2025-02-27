package com.mykyda.symon.api.service;

import com.mykyda.symon.api.database.entity.Profile;
import com.mykyda.symon.api.database.repository.ProfileRepository;
import com.mykyda.symon.security.database.entity.User;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    @Transactional
    public Profile createFromUser(User savedUser) {
        var profile = Profile.builder()
                .profilePicture(savedUser.getAvatar())
                .id(savedUser.getId())
                .backgroundColor("black")
                .mainColor("white")
                .secondaryColor("gray")
                .followed(0)
                .email(savedUser.getEmail())
                .following(0)
                .username(savedUser.getUsername())
                .build();
        return profileRepository.save(profile);
    }

    public void addProfileToModel(Model model, Principal principal) {
        var profile = profileRepository.findByEmail(principal.getName());
        if (profile.isPresent()){
            model.addAttribute("profile",profile.get());
        }
    }
}

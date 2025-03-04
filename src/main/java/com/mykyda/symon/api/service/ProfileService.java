package com.mykyda.symon.api.service;

import com.mykyda.symon.api.database.entity.Profile;
import com.mykyda.symon.api.database.repository.ProfileRepository;
import com.mykyda.symon.api.mapper.ToProfileDTOMapper;
import com.mykyda.symon.security.database.entity.User;
import com.mykyda.symon.security.dto.UserEditDTO;
import com.mykyda.symon.security.service.UserService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class ProfileService {

    private final ProfileRepository profileRepository;

    private final ShowReviewService showReviewService;

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
                .reviews(0)
                .username(savedUser.getUsername())
                .build();
        return profileRepository.save(profile);
    }

    public void addProfileToModel(Model model, Principal principal) {
        var profile = profileRepository.findByEmail(principal.getName());
        if (profile.isPresent()) {
            var profileDTO = ToProfileDTOMapper.map(profile.get(), showReviewService.findAllByProfileIdWithShow(profile.get().getId(), PageRequest.of(0, 7)));
            model.addAttribute("profile", profileDTO);
        } else {
            model.addAttribute("error", "unexpected error");
        }
    }

    public void patch(User updatedUser) {
        var patchedProfile = Profile.builder()
                .id(updatedUser.getId())
                .profilePicture(updatedUser.getAvatar())
                .username(updatedUser.getUsername())
                .build();
        profileRepository.save(patchedProfile);
    }
}

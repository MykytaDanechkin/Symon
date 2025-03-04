package com.mykyda.symon.api.http.controller;

import com.mykyda.symon.api.service.ProfileService;
import com.mykyda.symon.security.dto.UserEditDTO;
import com.mykyda.symon.security.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    private final UserService userService;

    @GetMapping()
    public String getProfile(Model model, Principal principal) {
        profileService.addProfileToModel(model, principal);
        return "profile";
    }

    @PatchMapping("/patch")
    public String editProfile(@RequestBody UserEditDTO userEditDTO) {
        var patcherdUser = userService.patch(userEditDTO);
        profileService.patch(patcherdUser);
        return "redirect:/profile";
    }
}

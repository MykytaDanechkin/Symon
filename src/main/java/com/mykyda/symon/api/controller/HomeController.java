package com.mykyda.symon.api.controller;

import com.mykyda.symon.api.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final ProfileService profileService;

    @GetMapping()
    public String getHome(Model model, Principal principal){
        profileService.addProfileToModel(model,principal);
        return "home";
    }
}

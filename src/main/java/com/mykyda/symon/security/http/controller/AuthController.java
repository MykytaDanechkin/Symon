package com.mykyda.symon.security.http.controller;

import com.mykyda.symon.security.dto.LoginDto;
import com.mykyda.symon.security.dto.RegistrationDefaultDto;
import com.mykyda.symon.security.service.AuthService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@ModelAttribute LoginDto ld, Model model, HttpServletResponse response) {
        var res = authService.login(ld, response);
        if (res.getStatusCode() != HttpStatus.OK) {
            model.addAttribute("errors", Collections.singletonList(res.getBody()));
            return "login";
        }
        return "redirect:/profile";
    }

    @GetMapping("/registration")
    public String regPage() {
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute RegistrationDefaultDto rdd, Model model) {
        var res = authService.register(rdd);
        if (res.getStatusCode() != HttpStatus.CREATED) {
            model.addAttribute("errors", Collections.singletonList(res.getBody()));
            return "registration";
        }
        return "redirect:/auth/login";
    }
}

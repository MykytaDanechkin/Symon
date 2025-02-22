package com.mykyda.symon.security.controller;

import com.mykyda.symon.security.dto.RegistrationDefaultDto;
import com.mykyda.symon.security.service.UserService;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;

@Controller
@RequestMapping("auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String loginPage(){
        return "login";
    }

    @GetMapping("/registration")
    public String regPage(){
        return "registration";
    }

    @PostMapping("/registration")
    public String register(@ModelAttribute RegistrationDefaultDto rdd, Model model) throws IOException {
        var res = userService.register(rdd);
        if (res.getStatusCode() != HttpStatus.CREATED) {
            model.addAttribute("errors", Collections.singletonList(res.getBody()));
            return "registration";
        }
        return "redirect:/auth/login";
    }
}

package com.mykyda.symon.api.http.controller;

import com.mykyda.symon.api.service.ShowReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ShowReviewService showReviewService;

    @GetMapping("/show/{id}")
    public String getReview(Model model, @PathVariable Long id){
        showReviewService.findByIdWithShowWithProfile(id,model);
        return "review";
    }
}

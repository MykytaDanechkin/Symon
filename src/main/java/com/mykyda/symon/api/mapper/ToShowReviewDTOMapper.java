package com.mykyda.symon.api.mapper;

import com.mykyda.symon.api.database.entity.ShowReview;
import com.mykyda.symon.api.dto.ShowReviewDTO;

public class ToShowReviewDTOMapper {

    public static ShowReviewDTO map(ShowReview showReview) {
        return ShowReviewDTO.builder()
                .score(showReview.getScore())
                .reviewTitle(showReview.getReviewTitle())
                .review(showReview.getReview())
                .show(showReview.getShow())
                .id(showReview.getId())
                .date(showReview.getDate())
                .profile(ToProfileDTOMapper.mapWithoutReviews(showReview.getProfile()))
                .build();
    }

    public static ShowReviewDTO mapWithoutProfile(ShowReview showReview) {
        return ShowReviewDTO.builder()
                .score(showReview.getScore())
                .reviewTitle(showReview.getReviewTitle())
                .review(showReview.getReview())
                .show(showReview.getShow())
                .id(showReview.getId())
                .date(showReview.getDate())
                .build();
    }

    public static ShowReviewDTO mapWithoutShow(ShowReview showReview) {
        return ShowReviewDTO.builder()
                .score(showReview.getScore())
                .reviewTitle(showReview.getReviewTitle())
                .review(showReview.getReview())
                .profile(ToProfileDTOMapper.mapWithoutReviews(showReview.getProfile()))
                .id(showReview.getId())
                .date(showReview.getDate())
                .build();
    }
}


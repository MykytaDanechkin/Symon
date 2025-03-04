package com.mykyda.symon.api.mapper;

import com.mykyda.symon.api.database.entity.Profile;
import com.mykyda.symon.api.dto.ProfileDTO;
import com.mykyda.symon.api.dto.ShowReviewDTO;

import java.util.List;

public class ToProfileDTOMapper {

    public static ProfileDTO map(Profile profile, List<ShowReviewDTO> showReviewDTOS) {
        return ProfileDTO.builder()
                .profilePicture(profile.getProfilePicture())
                .id(profile.getId())
                .followed(profile.getFollowed())
                .following(profile.getFollowing())
                .username(profile.getUsername())
                .showReviews(showReviewDTOS.isEmpty() ? null : showReviewDTOS)
                .build();
    }
    public static ProfileDTO mapWithoutReviews(Profile profile) {
        return ProfileDTO.builder()
                .profilePicture(profile.getProfilePicture())
                .id(profile.getId())
                .followed(profile.getFollowed())
                .following(profile.getFollowing())
                .username(profile.getUsername()).build();
    }
}

package com.mykyda.symon.api.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfileDTO {

    private Long id;

    private String username;

    private String profilePicture;

    private Integer following;

    private Integer followed;

    private List<ShowReviewDTO> showReviews;
}

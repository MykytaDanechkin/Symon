package com.mykyda.symon.api.dto;

import com.mykyda.symon.api.database.entity.Show;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShowReviewDTO {

    private Long id;

    private String reviewTitle;

    private String review;

    private Float score;

    private Show show;

    private ProfileDTO profile;

    private Timestamp date;
}

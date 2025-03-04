package com.mykyda.symon.api.database.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;

import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "show_review")
public class ShowReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="profile_id")
    Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    Show show;

    Float score;

    String review;

    String reviewTitle;

    @Temporal(TemporalType.TIMESTAMP)
    Timestamp date;
}

package com.mykyda.symon.api.database.entity;

import com.mykyda.symon.security.database.entity.User;
import jakarta.persistence.*;
import lombok.*;

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

    @ManyToOne
    @JoinColumn(name="profile_id", nullable=false)
    Profile profile;

    @ManyToOne
    Show show;

    Float score;

    String review;
}

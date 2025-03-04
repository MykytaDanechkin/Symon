package com.mykyda.symon.api.database.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Set;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "show")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String title;

    String genre;

    String description;

    String poster;

    String director;

    Float rottenScore;

    Float userScore;

    @Transient
    List<ShowReview> reviews;
}

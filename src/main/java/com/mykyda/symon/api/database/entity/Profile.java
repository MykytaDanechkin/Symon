package com.mykyda.symon.api.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "profile")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Profile {
    @Id
    Long id;

    String profilePicture;

    Integer following;

    Integer followed;

    String email;

    String username;

    String backgroundColor;

    String mainColor;

    String secondaryColor;

    String badge;

    Integer reviews;

    @Transient
    List<ShowReview> showReviews;

//    @ManyToMany
//    Set<Game> reviewedGames;
//
//    @ManyToMany
//    Set<Book> reviewedBook;
//
//    @ManyToMany
//    Set<Album> reviewedAlbum;
}

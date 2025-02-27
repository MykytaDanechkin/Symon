package com.mykyda.symon.api.database.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.Set;

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

    @OneToMany(mappedBy="profile")
    Set<ShowReview> showReviews;

//    @ManyToMany
//    Set<Game> reviewedGames;
//
//    @ManyToMany
//    Set<Book> reviewedBook;
//
//    @ManyToMany
//    Set<Album> reviewedAlbum;
}

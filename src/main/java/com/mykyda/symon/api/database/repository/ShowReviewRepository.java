package com.mykyda.symon.api.database.repository;

import com.mykyda.symon.api.database.entity.ShowReview;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowReviewRepository extends JpaRepository<ShowReview, Long> {

    Optional<ShowReview> findById(Long id);

    @Query("SELECT sr FROM ShowReview sr JOIN FETCH sr.show JOIN FETCH sr.profile WHERE sr.id = :id")
    Optional<ShowReview> findByIdWithShowWithProfile(@Param("id") Long id);

    @Query("SELECT sr FROM ShowReview sr JOIN FETCH sr.show WHERE sr.profile.id = :profileId ORDER BY sr.date DESC")
    Page<ShowReview> findAllByProfileIdWithShow(@Param("profileId") Long profileId, Pageable pageable);

    @Query("SELECT sr FROM ShowReview sr JOIN FETCH sr.profile WHERE sr.show.id = :showId")
    Page<ShowReview> findAllByShowIdWithProfile(@Param("showId") Long showId, Pageable pageable);

}

package com.mykyda.symon.api.database.repository;

import com.mykyda.symon.api.database.entity.ShowReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShowReviewRepository extends JpaRepository<ShowReview, Long> {
    Optional<ShowReview> findById(Long id);
}

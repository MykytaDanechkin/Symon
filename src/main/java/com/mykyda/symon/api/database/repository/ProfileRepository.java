package com.mykyda.symon.api.database.repository;

import com.mykyda.symon.api.database.entity.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Long> {

    Optional<Profile> findById(Long id);

    Optional<Profile> findByEmail(String email);
}

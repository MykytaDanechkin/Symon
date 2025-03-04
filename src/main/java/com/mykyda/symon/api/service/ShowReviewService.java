package com.mykyda.symon.api.service;

import com.mykyda.symon.api.database.repository.ShowReviewRepository;
import com.mykyda.symon.api.dto.ShowReviewDTO;
import com.mykyda.symon.api.mapper.ToShowReviewDTOMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ShowReviewService {

    private final ShowReviewRepository showReviewRepository;

    @Transactional
    public void findByIdWithShowWithProfile(Long id, Model model) {
        var review = showReviewRepository.findByIdWithShowWithProfile(id);
        if (review.isPresent()) {
            var reviewDto = ToShowReviewDTOMapper.map(review.get());
            model.addAttribute("review", reviewDto);
        }
    }

    @Transactional
    public List<ShowReviewDTO> findAllByProfileIdWithShow(Long profileId, Pageable pageable) {
        var reviews = showReviewRepository.findAllByProfileIdWithShow(profileId, pageable);
        if (!reviews.isEmpty()) {
            return reviews.map(ToShowReviewDTOMapper::mapWithoutProfile).stream().collect(Collectors.toList());
        } else {
            return null;
        }
    }

    @Transactional
    public List<ShowReviewDTO> findAllByShowIdWithProfile(Long showId, Pageable pageable) {
        var reviews = showReviewRepository.findAllByShowIdWithProfile(showId, pageable);
        if (!reviews.isEmpty()) {
            return reviews.map(ToShowReviewDTOMapper::mapWithoutShow).stream().collect(Collectors.toList());
        } else {
            return null;
        }
    }
}

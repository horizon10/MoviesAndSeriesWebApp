package com.horizon.service;

import com.horizon.entity.*;
import com.horizon.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesService {
    private final SeriesRepository seriesRepository;
    private final SeriesRatingRepository ratingRepository;
    private final SeriesReviewRepository reviewRepository;
    private final UserRepository userRepository;

    public Series getOrCreateSeries(String imdbId) {
        return seriesRepository.findByImdbId(imdbId)
                .orElseGet(() -> seriesRepository.save(new Series(imdbId)));
    }

    @Transactional
    public SeriesRating addRatingToSeries(String imdbId, String userId, double score) {
        Series series = getOrCreateSeries(imdbId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SeriesRating rating = ratingRepository.findBySeriesImdbIdAndUserId(imdbId, userId)
                .map(existing -> {
                    existing.setScore(score);
                    return existing;
                })
                .orElseGet(() -> new SeriesRating(userId, score, series));

        return ratingRepository.save(rating);
    }

    @Transactional
    public SeriesReview addReviewToSeries(String imdbId, String userId, String comment) {
        Series series = getOrCreateSeries(imdbId);
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        SeriesReview review = reviewRepository.findBySeriesImdbIdAndUserId(imdbId, userId)
                .map(existing -> {
                    existing.setComment(comment);
                    return existing;
                })
                .orElseGet(() -> new SeriesReview(userId, comment, series));

        return reviewRepository.save(review);
    }

    public List<SeriesRating> getSeriesRatings(String imdbId) {
        return ratingRepository.findBySeriesImdbId(imdbId);
    }

    public List<SeriesReview> getSeriesReviews(String imdbId) {
        return reviewRepository.findBySeriesImdbId(imdbId);
    }
}
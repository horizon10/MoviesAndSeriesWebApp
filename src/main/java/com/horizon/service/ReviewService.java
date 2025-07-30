package com.horizon.service;

import com.horizon.entity.Movie;
import com.horizon.entity.Review;
import com.horizon.repository.ReviewRepository;
import com.horizon.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final MovieRepository movieRepository;

    public Review addOrUpdateReview(String imdbId, String userId, String comment) {
        Movie movie = movieRepository.findByImdbId(imdbId)
                .orElseGet(() -> movieRepository.save(new Movie(imdbId)));

        Review review = reviewRepository.findByMovieImdbIdAndUserId(imdbId, userId)
                .map(existing -> {
                    existing.setComment(comment);
                    return existing;
                })
                .orElseGet(() -> new Review(userId, comment, movie));

        return reviewRepository.save(review);
    }

    public List<Review> getReviewsByMovie(String imdbId) {
        return reviewRepository.findByMovieImdbId(imdbId);
    }

    public void deleteReview(Long reviewId) {
        reviewRepository.deleteById(reviewId);
    }
}
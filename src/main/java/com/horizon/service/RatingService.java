package com.horizon.service;

import com.horizon.entity.Movie;
import com.horizon.entity.Rating;
import com.horizon.repository.RatingRepository;
import com.horizon.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RatingService {
    private final RatingRepository ratingRepository;
    private final MovieRepository movieRepository;

    public Rating addOrUpdateRating(String imdbId, String userId, double score) {
        Movie movie = movieRepository.findByImdbId(imdbId)
                .orElseGet(() -> movieRepository.save(new Movie(imdbId)));

        Rating rating = ratingRepository.findByMovieImdbIdAndUserId(imdbId, userId)
                .map(existing -> {
                    existing.setScore(score);
                    return existing;
                })
                .orElseGet(() -> new Rating(userId, score, movie));

        return ratingRepository.save(rating);
    }

    public List<Rating> getRatingsByMovie(String imdbId) {
        return ratingRepository.findByMovieImdbId(imdbId);
    }

    public void deleteRating(Long ratingId) {
        ratingRepository.deleteById(ratingId);
    }
}
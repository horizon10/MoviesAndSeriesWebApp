package com.horizon.service;

import com.horizon.entity.Movie;
import com.horizon.entity.Rating;
import com.horizon.entity.Review;
import com.horizon.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MovieService {
    private final MovieRepository movieRepository;

    public Movie getOrCreateMovie(String imdbId) {
        Optional<Movie> existingMovie = movieRepository.findByImdbId(imdbId);
        return existingMovie.orElseGet(() -> movieRepository.save(Movie.builder().imdbId(imdbId).build()));
    }

    public Rating addRatingToMovie(String imdbId, String userId, double score) {
        Movie movie = getOrCreateMovie(imdbId);
        Rating rating = Rating.builder()
                .userId(userId)
                .score(score)
                .movie(movie)
                .build();
        movie.getRatings().add(rating);
        movieRepository.save(movie);
        return rating;
    }

    public Review addReviewToMovie(String imdbId, String userId, String comment) {
        Movie movie = getOrCreateMovie(imdbId);
        Review review = Review.builder()
                .userId(userId)
                .comment(comment)
                .movie(movie)
                .build();
        movie.getReviews().add(review);
        movieRepository.save(movie);
        return review;
    }

    public List<Rating> getMovieRatings(String imdbId) {
        return movieRepository.findByImdbId(imdbId)
                .map(Movie::getRatings)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }

    public List<Review> getMovieReviews(String imdbId) {
        return movieRepository.findByImdbId(imdbId)
                .map(Movie::getReviews)
                .orElseThrow(() -> new RuntimeException("Movie not found"));
    }
}
package com.horizon.controller;

import com.horizon.entity.Rating;
import com.horizon.entity.Review;
import com.horizon.service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @PostMapping("/{imdbId}/ratings")
    public Rating addRating(@PathVariable String imdbId,
                            @RequestParam String userId,
                            @RequestParam double score) {
        return movieService.addRatingToMovie(imdbId, userId, score);
    }

    @PostMapping("/{imdbId}/reviews")
    public Review addReview(@PathVariable String imdbId,
                            @RequestParam String userId,
                            @RequestBody String comment) {
        return movieService.addReviewToMovie(imdbId, userId, comment);
    }

    @GetMapping("/{imdbId}/ratings")
    public List<Rating> getRatings(@PathVariable String imdbId) {
        return movieService.getMovieRatings(imdbId);
    }

    @GetMapping("/{imdbId}/reviews")
    public List<Review> getReviews(@PathVariable String imdbId) {
        return movieService.getMovieReviews(imdbId);
    }
}
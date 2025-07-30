package com.horizon.controller;

import com.horizon.entity.Review;
import com.horizon.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping
    public Review addReview(@RequestParam String imdbId,
                            @RequestParam String userId,
                            @RequestBody String comment) {
        return reviewService.addOrUpdateReview(imdbId, userId, comment);
    }

    @GetMapping("/movie/{imdbId}")
    public List<Review> getMovieReviews(@PathVariable String imdbId) {
        return reviewService.getReviewsByMovie(imdbId);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
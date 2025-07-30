package com.horizon.controller;

import com.horizon.entity.Rating;
import com.horizon.service.RatingService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@RequiredArgsConstructor
public class RatingController {
    private final RatingService ratingService;

    @PostMapping
    public Rating addRating(@RequestParam String imdbId,
                            @RequestParam String userId,
                            @RequestParam double score) {
        return ratingService.addOrUpdateRating(imdbId, userId, score);
    }

    @GetMapping("/movie/{imdbId}")
    public List<Rating> getMovieRatings(@PathVariable String imdbId) {
        return ratingService.getRatingsByMovie(imdbId);
    }

    @DeleteMapping("/{id}")
    public void deleteRating(@PathVariable Long id) {
        ratingService.deleteRating(id);
    }
}
package com.horizon.controller;

import com.horizon.entity.*;
import com.horizon.service.SeriesService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/series")
@RequiredArgsConstructor
public class SeriesController {
    private final SeriesService seriesService;

    @PostMapping("/{imdbId}/ratings")
    public SeriesRating addRating(@PathVariable String imdbId,
                                  @RequestParam String userId,
                                  @RequestParam double score) {
        return seriesService.addRatingToSeries(imdbId, userId, score);
    }

    @PostMapping("/{imdbId}/reviews")
    public SeriesReview addReview(@PathVariable String imdbId,
                                  @RequestParam String userId,
                                  @RequestBody String comment) {
        return seriesService.addReviewToSeries(imdbId, userId, comment);
    }

    @GetMapping("/{imdbId}/ratings")
    public List<SeriesRating> getRatings(@PathVariable String imdbId) {
        return seriesService.getSeriesRatings(imdbId);
    }

    @GetMapping("/{imdbId}/reviews")
    public List<SeriesReview> getReviews(@PathVariable String imdbId) {
        return seriesService.getSeriesReviews(imdbId);
    }
}
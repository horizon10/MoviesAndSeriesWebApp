package com.horizon.controller;

import com.horizon.dto.CommentDTO;
import com.horizon.dto.RatingDTO;
import com.horizon.entity.Favorite;
import com.horizon.entity.User;
import com.horizon.service.CommentService;
import com.horizon.service.FavoriteService;
import com.horizon.service.RatingService;
import com.horizon.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home/")
@RequiredArgsConstructor
public class MovieInteractionController {

    private final UserService userService;
    private final FavoriteService favoriteService;
    private final CommentService commentService;
    private final RatingService ratingService;


    private User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName(); // Giriş yapan kullanıcının username'i
        return userService.getByUsername(username);
    }


    // FAVORITE

    @PostMapping("/favorite/{imdbId}")
    public ResponseEntity<Void> favoriteMovie(@PathVariable String imdbId) {
        favoriteService.addFavorite(getCurrentUser(), imdbId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/favorite/{imdbId}")
    public ResponseEntity<Void> unfavoriteMovie(@PathVariable String imdbId) {
        favoriteService.removeFavorite(getCurrentUser(), imdbId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/favorite")
    public ResponseEntity<List<Favorite>> getFavorites() {
        return ResponseEntity.ok(favoriteService.getFavorites(getCurrentUser()));
    }

    // COMMENT

    @PostMapping("/comment/{imdbId}")
    public ResponseEntity<Void> comment(@PathVariable String imdbId, @RequestBody String content) {
        commentService.addComment(getCurrentUser(), imdbId, content);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/comment/{imdbId}")
    public ResponseEntity<List<CommentDTO>> getComments(@PathVariable String imdbId) {
        return ResponseEntity.ok(commentService.getComments(imdbId));
    }

    // RATING

    @PostMapping("/rate/{imdbId}")
    public ResponseEntity<Void> rate(@PathVariable String imdbId, @RequestBody Integer score) {
        ratingService.rateMovie(getCurrentUser(), imdbId, score); // Güncellerse update
        return ResponseEntity.ok().build();
    }

    @GetMapping("/rate/{imdbId}")
    public ResponseEntity<List<RatingDTO>> getRatings(@PathVariable String imdbId) {
        return ResponseEntity.ok(ratingService.getRatings(imdbId));
    }

    @GetMapping("/rate/{imdbId}/average")
    public ResponseEntity<Double> getAverageRating(@PathVariable String imdbId) {
        Double average = ratingService.getAverageRating(imdbId);
        return ResponseEntity.ok(average != null ? average : 0.0);
    }
}

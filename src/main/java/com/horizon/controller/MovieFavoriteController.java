package com.horizon.controller;

import com.horizon.entity.MovieFavorite;
import com.horizon.service.MovieFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movie/favorites")
@RequiredArgsConstructor
public class MovieFavoriteController {
    private final MovieFavoriteService favoriteService;

    @GetMapping
    public ResponseEntity<List<MovieFavorite>> getAll(){
        return ResponseEntity.ok(favoriteService.getAllFavorites());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<MovieFavorite>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(favoriteService.getByUserId(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MovieFavorite create(@RequestBody MovieFavorite favorite){
        return favoriteService.save(favorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        favoriteService.delete(id);
        return ResponseEntity.ok().build();
    }



}

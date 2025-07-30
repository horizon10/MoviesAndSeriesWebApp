package com.horizon.controller;

import com.horizon.entity.SeriesFavorite;
import com.horizon.service.SeriesFavoriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/series/favorites")
@RequiredArgsConstructor
public class SeriesFavoriteController {
    private final SeriesFavoriteService seriesFavoriteService;

    @GetMapping
    public ResponseEntity<List<SeriesFavorite>> getAll(){
        return ResponseEntity.ok(seriesFavoriteService.getAllFavorites());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<SeriesFavorite>> getByUserId(@PathVariable Long userId){
        return ResponseEntity.ok(seriesFavoriteService.getByUserId(userId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SeriesFavorite create(@RequestBody SeriesFavorite favorite){
        return seriesFavoriteService.save(favorite);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        seriesFavoriteService.delete(id);
        return ResponseEntity.ok().build();
    }
}

package com.horizon.controller;

import com.horizon.entity.OmdbResponse;
import com.horizon.service.OmdbService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/omdb")
@RequiredArgsConstructor
public class OmdbController {
    private final OmdbService omdbService;

    @GetMapping("/search")
    public ResponseEntity<OmdbResponse> search(@RequestParam String title) {
        return ResponseEntity.ok(omdbService.searchMovieByTitle(title));
    }
}

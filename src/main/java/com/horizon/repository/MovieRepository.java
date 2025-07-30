package com.horizon.repository;

import com.horizon.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository extends JpaRepository<Movie, Long> {
 Optional<Movie> findByImdbId(String imdbId);
}
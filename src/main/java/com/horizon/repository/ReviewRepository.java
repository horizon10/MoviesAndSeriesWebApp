package com.horizon.repository;

import com.horizon.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByMovieImdbId(String imdbId);
    Optional<Review> findByMovieImdbIdAndUserId(String imdbId, String userId);
}
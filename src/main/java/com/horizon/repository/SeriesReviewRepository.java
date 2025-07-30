package com.horizon.repository;

import com.horizon.entity.Series;
import com.horizon.entity.SeriesReview;
import com.horizon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SeriesReviewRepository extends JpaRepository<SeriesReview, Long> {
    List<SeriesReview> findBySeriesImdbId(String imdbId);
    Optional<SeriesReview> findBySeriesImdbIdAndUserId(String imdbId, String userId);
    boolean existsBySeriesAndUser(Series series, User user);
}
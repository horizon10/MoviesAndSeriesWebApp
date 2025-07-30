package com.horizon.repository;

import com.horizon.entity.Series;
import com.horizon.entity.SeriesRating;
import com.horizon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface SeriesRatingRepository extends JpaRepository<SeriesRating, Long> {
    List<SeriesRating> findBySeriesImdbId(String imdbId);
    Optional<SeriesRating> findBySeriesImdbIdAndUserId(String imdbId, String userId);
    boolean existsBySeriesAndUser(Series series, User user);
    int countBySeries(Series series);
}
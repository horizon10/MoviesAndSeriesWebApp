package com.horizon.repository;

import com.horizon.entity.User;

import com.horizon.entity.SeriesFavorite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SeriesFavoriteRepository extends JpaRepository<SeriesFavorite, Long> {
    List<SeriesFavorite> findByUser(User user);
    List<SeriesFavorite> findByUserId(Long id);
}

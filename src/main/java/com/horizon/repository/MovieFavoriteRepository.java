package com.horizon.repository;

import com.horizon.entity.MovieFavorite;
import com.horizon.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieFavoriteRepository extends JpaRepository<MovieFavorite, Long> {
    List<MovieFavorite> findByUser(User user);
    List<MovieFavorite> findByUserId(Long id);
}

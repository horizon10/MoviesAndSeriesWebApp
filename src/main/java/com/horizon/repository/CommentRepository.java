package com.horizon.repository;

import com.horizon.entity.Favorite;
import com.horizon.entity.Comment;
import com.horizon.entity.Rating;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByImdbId(String imdbId);
}
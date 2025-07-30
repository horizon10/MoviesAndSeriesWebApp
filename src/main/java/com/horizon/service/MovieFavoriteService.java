package com.horizon.service;

import com.horizon.entity.MovieFavorite;
import com.horizon.entity.Movie;
import com.horizon.entity.User;
import com.horizon.repository.MovieFavoriteRepository;
import com.horizon.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieFavoriteService {

    private final MovieFavoriteRepository moviefavoriteRepository;
    private final MovieRepository movieRepository;

    public List<MovieFavorite> getAllFavorites() {
        return moviefavoriteRepository.findAll();
    }

    public List<MovieFavorite> getByUserId(Long userId) {
        return moviefavoriteRepository.findByUserId(userId);
    }

    public MovieFavorite save(MovieFavorite favorite) {
        return moviefavoriteRepository.save(favorite);
    }

    public void delete(Long id) {
        moviefavoriteRepository.deleteById(id);
    }
    public void addFavorite(User user, Movie movie) {
        // Film veritabanında yoksa ekle
        movieRepository.save(movie);

        MovieFavorite favorite = new MovieFavorite();
        favorite.setUser(user);
        favorite.setMovie(movie);
        favorite.setAddedAt(LocalDateTime.now());
        moviefavoriteRepository.save(favorite);
    }

}

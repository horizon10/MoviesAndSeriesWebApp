package com.horizon.service;

import com.horizon.entity.*;
import com.horizon.repository.SeriesFavoriteRepository;
import com.horizon.repository.SeriesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SeriesFavoriteService {

    private final SeriesFavoriteRepository seriesFavoriteRepository;
    private final SeriesRepository seriesRepository;

    public List<SeriesFavorite> getAllFavorites() {
        return seriesFavoriteRepository.findAll();
    }

    public List<SeriesFavorite> getByUserId(Long userId) {
        return seriesFavoriteRepository.findByUserId(userId);
    }

    public SeriesFavorite save(SeriesFavorite seriesFavorite) {
        return seriesFavoriteRepository.save(seriesFavorite);
    }

    public void delete(Long id) {
        seriesFavoriteRepository.deleteById(id);
    }
    public void addFavorite(User user, Series series) {
        // Film veritabanında yoksa ekle
        seriesRepository.save(series);

        SeriesFavorite favorite = new SeriesFavorite();
        favorite.setUser(user);
        favorite.setSeries(series);
        favorite.setAddedAt(LocalDateTime.now());
        seriesFavoriteRepository.save(favorite);
    }
}

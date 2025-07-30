package com.horizon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "series_ratings")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeriesRating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private double score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    public SeriesRating(String userId, double score, Series series) {
        this.userId = userId;
        this.score = score;
        this.series = series;
    }
}
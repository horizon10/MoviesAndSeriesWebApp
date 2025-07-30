package com.horizon.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "series_reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SeriesReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String userId;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "series_id")
    private Series series;

    public SeriesReview(String userId, String comment, Series series) {
        this.userId = userId;
        this.comment = comment;
        this.series = series;
    }
}
package com.horizon.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RatingDTO {
    private String username;
    private int score;
}

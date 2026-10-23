package com.cinevault.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record UserMovieResponse(
    Integer id,
    Integer movieId,
    String movieTitle,
    String status,
    Integer priority,
    BigDecimal personalRating,
    String personalReview,
    LocalDateTime addedAt
) {
}
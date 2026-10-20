package com.cinevault.dto;

import java.math.BigDecimal;

public record UserMovieRequest(

    Integer tmdbId,
    String status,
    Integer priority,
    BigDecimal personalRating,
    String personalReview

) {
    
}

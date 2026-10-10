package com.cinevault.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbMovieResponse(

    @JsonProperty("id")
    Integer externalId,

    String title,

    @JsonProperty ("original_title")
    String originalTitle,

    String overview,

    @JsonProperty ("release_date")
    LocalDate releaseDate,

    @JsonProperty ("runtime")
    Integer runtimeMinutes

) {
    
}

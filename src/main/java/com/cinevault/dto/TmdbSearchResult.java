package com.cinevault.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbSearchResult(

    @JsonProperty("id")
    Integer externalId,

    String title,

    @JsonProperty ("original_title")
    String originalTitle,

    @JsonProperty ("release_date")
    LocalDate releaseDate

) {
    
}
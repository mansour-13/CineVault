package com.cinevault.dto;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbPersonResponse(
    
    @JsonProperty("id")
    Integer externalId,

    String name,

    String biography,
    
    @JsonProperty("place_of_birth")
    String placeOfBirth,

    @JsonProperty("birthday")
    LocalDate dateOfBirth
) {
    
}

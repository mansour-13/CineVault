package com.cinevault.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbSearchResponse(

    Integer page,

    @JsonProperty("total_results")
    Integer totalResults,

    @JsonProperty("total_pages")
    Integer totalPages,

    List<TmdbSearchResult> results

) {
}
package com.cinevault.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

public record TmdbCreditResponse(

    List<TmdbCastResponse> cast,
    List<TmdbCrewResponse> crew
) {
    
    public record TmdbCastResponse(

        @JsonProperty("id")
        Integer externalId,

        String name,

        @JsonProperty("character")
        String characterName,

        @JsonProperty("order")
        Integer creditOrder
    ) {
        
    }

    public record TmdbCrewResponse(

        @JsonProperty("id")
        Integer externalId,

        String name,

        String job
    ) {
        
    }
}

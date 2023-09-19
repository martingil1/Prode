package com.example.prode.responses;

import com.example.prode.models.Tourney;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;

@Builder
public class TourneyResponse {

    @JsonProperty("Nombre_torneo")
    String name;
    @JsonProperty("Año_torneo")
    Long year;

    public static TourneyResponse fromTourney(Tourney tourney){

        return TourneyResponse.builder()
                .name(tourney.getName())
                .year(tourney.getYearTourney())
                .build();

    }
}

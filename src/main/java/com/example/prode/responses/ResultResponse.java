package com.example.prode.responses;

import com.example.prode.models.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResultResponse {

    @JsonProperty("equipo_local")
    String localTeam;
    @JsonProperty("equipo_visitante")
    String visitingTeam;
    @JsonProperty("gol_equipo_local")
    Integer golLocalTeam;
    @JsonProperty("gol_equipo_visitante")
    Integer golVisitingTeam;

    public static ResultResponse mapFromResult(Result result){

        return ResultResponse.builder()
                .localTeam(result.getLocalTeam())
                .visitingTeam(result.getVisitingTeam())
                .golLocalTeam(result.getGolLocalTeam())
                .golVisitingTeam(result.getGolVisitingTeam())
                .build();

    }

}

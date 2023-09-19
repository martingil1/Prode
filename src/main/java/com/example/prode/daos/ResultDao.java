package com.example.prode.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultDao {

    @JsonProperty("equipo_local")
    String localTeam;
    @JsonProperty("equipo_visitante")
    String visitingTeam;
    @JsonProperty("gol_equipo_local")
    Integer golLocalTeam;
    @JsonProperty("gol_equipo_visitante")
    Integer golVisitingTeam;

}

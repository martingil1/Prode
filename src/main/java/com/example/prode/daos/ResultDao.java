package com.example.prode.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ResultDao {

    @JsonProperty("name")
    String name;
    @JsonProperty("local_team")
    String localTeam;
    @JsonProperty("visiting_team")
    String visitingTeam;
    @JsonProperty("gol_local_team")
    Integer golLocalTeam;
    @JsonProperty("gol_visiting_team")
    Integer golVisitingTeam;

}

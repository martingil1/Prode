package com.example.prode.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class TourneyDao {

    @JsonProperty("nombre_torneo")
    String name;
    @JsonProperty("año")
    Long year;
    @JsonProperty()
    Integer fecha;
    @JsonProperty("resultados")
    List<ResultDao> results;
}

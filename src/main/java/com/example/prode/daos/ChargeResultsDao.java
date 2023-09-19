package com.example.prode.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChargeResultsDao {

    @JsonProperty("nombre_torneo")
    String nameTourney;
    @JsonProperty("año")
    Long year;
    @JsonProperty()
    Integer fecha;
    @JsonProperty("nombre_usuario")
    String nameUser;
    @JsonProperty("resultados")
    List<ResultDao> results;
}

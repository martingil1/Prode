package com.example.prode.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
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

    public ChargeResultsDao(String nameTourney, Long year, Integer fecha, String nameUser) {
        this.nameTourney = nameTourney;
        this.year = year;
        this.fecha = fecha;
        this.nameUser = nameUser;
    }
}

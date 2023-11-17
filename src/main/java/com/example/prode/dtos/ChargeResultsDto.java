package com.example.prode.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ChargeResultsDto {

    @JsonProperty("nombre_torneo")
    String nameTourney;
    @JsonProperty("año")
    Long year;
    @JsonProperty()
    Integer fecha;
    @JsonProperty("nombre_usuario")
    String nameUser;
    @JsonProperty("resultados")
    List<ResultDto> results;

    public ChargeResultsDto(String nameTourney, Long year, Integer fecha, String nameUser) {
        this.nameTourney = nameTourney;
        this.year = year;
        this.fecha = fecha;
        this.nameUser = nameUser;
    }

    public ChargeResultsDto(String nameTourney, Long year, String nameUser) {
        this.nameTourney = nameTourney;
        this.year = year;
        this.nameUser = nameUser;
    }

    public ChargeResultsDto(String nameTourney, Long year, Integer fecha) {
        this.nameTourney = nameTourney;
        this.year = year;
        this.fecha = fecha;
    }

    public ChargeResultsDto(String nameTourney, Long year) {
        this.nameTourney = nameTourney;
        this.year = year;
    }
}

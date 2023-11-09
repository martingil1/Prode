package com.example.prode.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ChargeResultsFechaDto {

    @JsonProperty("nombre_torneo")
    String nameTourney;
    @JsonProperty("año")
    Long year;
    @JsonProperty()
    Integer fecha;
    @JsonProperty("resultados")
    List<ResultDto> results;

}

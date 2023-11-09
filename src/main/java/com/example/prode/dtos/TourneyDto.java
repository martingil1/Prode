package com.example.prode.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TourneyDto {

    @JsonProperty("nombre")
    String name;

    @JsonProperty("anio")
    Long year;
}

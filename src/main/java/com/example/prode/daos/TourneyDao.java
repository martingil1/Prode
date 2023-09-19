package com.example.prode.daos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TourneyDao {

    @JsonProperty("nombre")
    String name;

    @JsonProperty("anio")
    Long year;
}

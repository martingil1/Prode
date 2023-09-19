package com.example.prode.responses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SumResultResponse {

    @JsonProperty("torneo")
    public String tourney;

    @JsonProperty("fecha")
    public String fecha;

    @JsonProperty("nombre")
    public String name;

    @JsonProperty("suma_total")
    public Integer sumResult;

    public void setNombre(String name) {
        this.name = name;
    }

    public void setSumResult(Integer sumResult) {
        this.sumResult = sumResult;
    }

    public void setTourney(String tourney) {
        this.tourney = tourney;
    }

}

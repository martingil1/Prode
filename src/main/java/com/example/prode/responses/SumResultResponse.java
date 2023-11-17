package com.example.prode.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SumResultResponse {

    @JsonProperty("torneo")
    public String tourney;

    @JsonProperty("año_torneo")
    public Long year;

    @JsonProperty("fecha")
    public Integer fecha;

    @JsonProperty("nombre")
    public String name;

    @JsonProperty("suma_total")
    public Integer sumResult;

}

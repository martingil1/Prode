package com.example.prode.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Setter;

import java.util.Map;

@Builder
@Setter
public class PositionsResponse {

    @JsonProperty("torneo")
    public String tourney;

    @JsonProperty("año_torneo")
    public Long year;

    @JsonProperty("fecha")
    public Integer fecha;

    @JsonProperty("posiciones")
    public Map<String, Integer> users;

}

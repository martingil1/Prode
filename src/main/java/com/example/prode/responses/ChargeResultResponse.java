package com.example.prode.responses;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.models.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeResultResponse {

    @JsonProperty("torneo")
    String tourney;
    @JsonProperty()
    Integer fecha;
    @JsonProperty("nombre_usuario")
    String nameUser;
    @JsonProperty("resultados")
    public List<ResultResponse> results;

    public static ChargeResultResponse fromResult(ChargeResultsDto chargeResultsDto, List<Result> results){

        return ChargeResultResponse.builder()
                .tourney(chargeResultsDto.getNameTourney())
                .fecha(chargeResultsDto.getFecha())
                .nameUser(chargeResultsDto.getNameUser())
                .results(results.stream()
                        .map(ResultResponse::mapFromResult)
                        .collect(Collectors.toList()))
                .build();
    }

}

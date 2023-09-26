package com.example.prode.responses;

import com.example.prode.daos.ChargeResultsDao;
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

    public static ChargeResultResponse fromResult(ChargeResultsDao chargeResultsDao, List<Result> results){

        return ChargeResultResponse.builder()
                .tourney(chargeResultsDao.getNameTourney())
                .fecha(chargeResultsDao.getFecha())
                .nameUser(chargeResultsDao.getNameUser())
                .results(results.stream()
                        .map(ResultResponse::mapFromResult)
                        .collect(Collectors.toList()))
                .build();
    }

}

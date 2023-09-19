package com.example.prode.responses;

import com.example.prode.models.Result;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    public static ChargeResultResponse fromResult(Result result, List<ResultResponse> resultResponses){

        return ChargeResultResponse.builder()
                .results(resultResponses)
                .build();
    }

}

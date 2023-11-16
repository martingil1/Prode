package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.models.Result;
import com.example.prode.responses.SumResultResponse;

import java.util.List;

public interface ScoreService {

    void saveScore(ChargeResultsDto chargeResultsDto, List<Result> results);

    SumResultResponse showScorePartialByFechaAndUser(ChargeResultsDto chargeResultsDto);

    SumResultResponse showScorePartialByTourneyAndUser(ChargeResultsDto chargeResultsDto);

    //calcular posiciones

}

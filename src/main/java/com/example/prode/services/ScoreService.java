package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.models.Result;

import java.util.List;

public interface ScoreService {

    void saveScore(ChargeResultsDto chargeResultsDto, List<Result> results);

    //mostrar resultado parcial fecha por usuario
    //mostrar resultado total fecha por usuario
    //mostrar resultado parcial torneo por usuario
    //mostrar resultado total torneo por usuario
    //calcular posiciones

}

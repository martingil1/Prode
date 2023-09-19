package com.example.prode.services;

import com.example.prode.daos.ChargeResultsDao;
import com.example.prode.models.Result;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.SumResultResponse;

import java.util.List;

public interface ResultService {

    ChargeResultResponse chargeResult(ChargeResultsDao chargeResultsDao);
    List<Result> getResultByFecha();
    SumResultResponse getResultByUser(String user, String tourney, String year, Integer fecha);

}

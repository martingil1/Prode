package com.example.prode.services;

import com.example.prode.daos.TourneyDao;
import com.example.prode.models.Result;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.ResultResponse;

import java.util.List;

public interface ResultService {

    ChargeResultResponse chargeResult(TourneyDao tourneyDao);
    List<Result> getResultByFecha();
    ResultResponse getResultByUser(String user, String fecha);

}

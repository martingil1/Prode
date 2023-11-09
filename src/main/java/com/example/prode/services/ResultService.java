package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.models.Result;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.SumResultResponse;

import java.util.List;

public interface ResultService {

    ChargeResultResponse chargeResult(ChargeResultsDto chargeResultsDto);
    List<Result> getResultByFecha();
    SumResultResponse getResultByUser(ChargeResultsDto chargeResultsDto);

}

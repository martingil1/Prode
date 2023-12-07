package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.responses.ChargeResultResponse;

public interface ResultService {

    ChargeResultResponse chargeResult(ChargeResultsDto chargeResultsDto);

}

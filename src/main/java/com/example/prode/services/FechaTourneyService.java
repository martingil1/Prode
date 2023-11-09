package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsFechaDto;
import com.example.prode.responses.ChargeResultResponse;

public interface FechaTourneyService {

    ChargeResultResponse chargeResultFecha(ChargeResultsFechaDto chargeResultsFechaDto);

}

package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsFechaDto;
import com.example.prode.responses.ChargeResultResponse;

public interface FechaTourneyService {

    ChargeResultResponse chargeResultFecha(ChargeResultsFechaDto chargeResultsFechaDto);

    ChargeResultResponse chargeFechaEmpty(ChargeResultsFechaDto chargeResultsFechaDto);

    void deleteFechaTourney(Integer fecha, String tourney, Long year);

}

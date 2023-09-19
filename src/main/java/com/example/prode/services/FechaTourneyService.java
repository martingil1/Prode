package com.example.prode.services;

import com.example.prode.daos.ChargeResultsFechaDao;
import com.example.prode.responses.ChargeResultResponse;

public interface FechaTourneyService {

    ChargeResultResponse chargeResultFecha(ChargeResultsFechaDao chargeResultsFechaDao);

}

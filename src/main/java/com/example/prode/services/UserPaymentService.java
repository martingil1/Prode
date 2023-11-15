package com.example.prode.services;

import com.example.prode.dtos.PaymentDto;

public interface UserPaymentService {

    void chargePaymentTourneyOfUser(PaymentDto paymentDto);

    void chargePaymentFechaTourneyOfUser(PaymentDto paymentDto);

}

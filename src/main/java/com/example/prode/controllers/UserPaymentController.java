package com.example.prode.controllers;

import com.example.prode.dtos.PaymentDto;
import com.example.prode.services.UserPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/payment")
public class UserPaymentController {

    @Autowired
    UserPaymentService userPaymentService;

    @PostMapping(value = "/pagar-torneo",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> payTourney(@RequestBody PaymentDto paymentDto) {
        try {
            userPaymentService.chargePaymentTourneyOfUser(paymentDto);
        }catch (NullPointerException e){
            e.getMessage();
        }
        return ResponseEntity.ok("Pago del torneo "+paymentDto.getNameTourney() +" - " +paymentDto.getYearTourney());
    }

    @PostMapping(value = "/pagar-fecha",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<String> payFechaTourney(@RequestBody PaymentDto paymentDto) {

        try {
            userPaymentService.chargePaymentFechaTourneyOfUser(paymentDto);
        }catch (NullPointerException e){
            e.getMessage();
        }
        return ResponseEntity.ok("Pago de la fecha " + paymentDto.getFechaTourney());
    }

}

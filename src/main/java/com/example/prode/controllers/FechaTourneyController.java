package com.example.prode.controllers;

import com.example.prode.daos.ChargeResultsFechaDao;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.services.FechaTourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fecha")
public class FechaTourneyController {

    @Autowired
    FechaTourneyService fechaTourneyService;

    @PostMapping(value = "/cargar-fecha",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ChargeResultResponse> chargeFecha(@RequestBody ChargeResultsFechaDao chargeResultsFechaDao){

        return ResponseEntity.ok(fechaTourneyService.chargeResultFecha(chargeResultsFechaDao));

    }

}



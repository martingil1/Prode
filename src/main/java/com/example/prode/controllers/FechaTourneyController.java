package com.example.prode.controllers;

import com.example.prode.dtos.ChargeResultsFechaDto;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.PositionsResponse;
import com.example.prode.services.FechaTourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/fecha")
public class FechaTourneyController {

    @Autowired
    FechaTourneyService fechaTourneyService;

    @PostMapping(value = "/cargar-fecha-vacia",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ChargeResultResponse> chargeFecha(@RequestBody ChargeResultsFechaDto chargeResultsFechaDto){

        return ResponseEntity.ok(fechaTourneyService.chargeFechaEmpty(chargeResultsFechaDto));

    }

    @PostMapping(value = "/cargar-fecha-resultados",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ChargeResultResponse> chargeFechaWithResults(@RequestBody ChargeResultsFechaDto chargeResultsFechaDto){

        return ResponseEntity.ok(fechaTourneyService.chargeResultFecha(chargeResultsFechaDto));

    }

    @GetMapping(value = "/eliminar-fecha/{tourney}/{year}/{fecha}",
            produces = { MediaType.APPLICATION_JSON_VALUE})

        public ResponseEntity<String> deleteFechaTourney(@PathVariable String tourney,
                                                                       @PathVariable Long year,
                                                                       @PathVariable Integer fecha){

        fechaTourneyService.deleteFechaTourney(fecha,tourney,year);
        return ResponseEntity.ok("Fecha eliminada");
    }

}



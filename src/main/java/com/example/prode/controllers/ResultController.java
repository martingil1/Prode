package com.example.prode.controllers;

import com.example.prode.daos.ChargeResultsDao;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.SumResultResponse;
import com.example.prode.models.Result;
import com.example.prode.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class ResultController {

    @Autowired
    ResultService resultService;

    @PostMapping(value = "/cargar-resultado",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ChargeResultResponse> chargeResult(@RequestBody ChargeResultsDao chargeResultsDao){

        ChargeResultResponse response = resultService.chargeResult(chargeResultsDao);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping(value = "/mostrar-resultado/{nameUser}/{tourney}/{year}/{fecha}",  produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SumResultResponse> getResultByDB(@PathVariable String nameUser,
                                                           @PathVariable String tourney,
                                                           @PathVariable Long year,
                                                           @PathVariable Integer fecha){

        return ResponseEntity.ok(resultService.getResultByUser(new ChargeResultsDao(tourney, year, fecha, nameUser)));

    }

    @GetMapping(value = "/mostrar-resultados",  produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Result>> getResultByDB(){

        return ResponseEntity.ok(resultService.getResultByFecha());
    }

}

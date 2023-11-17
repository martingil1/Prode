package com.example.prode.controllers;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.services.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<ChargeResultResponse> chargeResult(@RequestBody ChargeResultsDto chargeResultsDto){

        ChargeResultResponse response = resultService.chargeResult(chargeResultsDto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}

package com.example.prode.controllers;

import com.example.prode.daos.TourneyDao;
import com.example.prode.responses.TourneyResponse;
import com.example.prode.services.TourneyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tourney")
public class TourneyController {

    @Autowired
    TourneyService tourneyService;

    @PostMapping(value = "/cargar-torneo",
            consumes = { MediaType.APPLICATION_JSON_VALUE},
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TourneyResponse> chargeTourney(@RequestBody TourneyDao tourneyDao){

        TourneyResponse response = tourneyService.chargeTourney(tourneyDao);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

}

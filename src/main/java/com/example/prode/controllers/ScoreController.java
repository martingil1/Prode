package com.example.prode.controllers;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.responses.PositionsResponse;
import com.example.prode.responses.SumResultResponse;
import com.example.prode.services.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/score")
public class ScoreController {


    @Autowired
    ScoreService scoreService;

    @GetMapping(value = "/suma-fecha/{nameUser}/{tourney}/{year}/{fecha}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SumResultResponse> getScoreByFecha(@PathVariable String nameUser,
                                                           @PathVariable String tourney,
                                                           @PathVariable Long year,
                                                           @PathVariable Integer fecha) {

        return ResponseEntity.ok(scoreService.showScorePartialByFechaAndUser(
                new ChargeResultsDto(tourney, year, fecha, nameUser)));
    }

    @GetMapping(value = "/suma-torneo/{nameUser}/{tourney}/{year}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<SumResultResponse> getScoreByTourney(@PathVariable String nameUser,
                                                               @PathVariable String tourney,
                                                               @PathVariable Long year){

        return ResponseEntity.ok(scoreService.showScorePartialByTourneyAndUser(
                new ChargeResultsDto(tourney, year, nameUser)));
    }

    @GetMapping(value = "/posiciones-fecha/{tourney}/{year}/{fecha}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PositionsResponse> getPositionsByFecha(@PathVariable String tourney,
                                                                 @PathVariable Long year,
                                                                 @PathVariable Integer fecha){

        return ResponseEntity.ok(scoreService.showPositionsByFecha(
                new ChargeResultsDto(tourney, year, fecha)));
    }

    @GetMapping(value = "/posiciones-torneo/{tourney}/{year}",
            produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<PositionsResponse> getPositionsByFecha(@PathVariable String tourney,
                                                                 @PathVariable Long year){

        return ResponseEntity.ok(scoreService.showPositionsByTourney(
                new ChargeResultsDto(tourney, year)));
    }
}

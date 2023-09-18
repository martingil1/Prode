package com.example.prode.controllers;

import com.example.prode.daos.ResultDao;
import com.example.prode.daos.TourneyDao;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.ResultResponse;
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
    public ResponseEntity<ChargeResultResponse> chargeResult(@RequestBody TourneyDao tourneyDao){

        ChargeResultResponse response = resultService.chargeResult(tourneyDao);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /*@GetMapping(value = "/obtener-resultado/{nombre}",  produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultadoResponse> getResult(@PathVariable String nombre){


        Integer suma = resultService.calcularResultado(nombre);
        //System.out.println("Jugador: "+nombre +" Puntos: " + suma);

        ResultadoResponse aux = new ResultadoResponse();
        aux.setNombre(nombre);
        aux.setSumaResultado(suma);

        return ResponseEntity.ok(aux);
    }*/

    @GetMapping(value = "/mostrar-resultado/{nombre}/{fecha}",  produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ResultResponse> getResultByDB(@PathVariable String nombre,
                                                        @PathVariable String fecha){

        return ResponseEntity.ok(resultService.getResultByUser(nombre, fecha));

    }

    @GetMapping(value = "/mostrar-resultado",  produces = { MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<Result>> getResultByDB(){

        return ResponseEntity.ok(resultService.getResultByFecha());
    }

}

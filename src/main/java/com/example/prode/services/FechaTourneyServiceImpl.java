package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsFechaDto;
import com.example.prode.dtos.ResultDto;
import com.example.prode.exceptions.FechasIsAlreadyLoadedException;
import com.example.prode.exceptions.TourneyNotExistException;
import com.example.prode.models.FechaTourney;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import com.example.prode.repositories.FechaTourneyRepository;
import com.example.prode.repositories.TourneyRepository;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FechaTourneyServiceImpl implements FechaTourneyService{

    @Autowired
    FechaTourneyRepository fechaTourneyRepository;
    @Autowired
    TourneyRepository tourneyRepository;

    public static final String FECHA = "Fecha ";

    @Override
    public ChargeResultResponse chargeResultFecha(ChargeResultsFechaDto chargeResultsFechaDto) {

        List<Result> results = new ArrayList<>();
        ChargeResultResponse response = new ChargeResultResponse();

        Tourney tourney =
                tourneyRepository.getTourneyByNameAndYearTourney(
                        chargeResultsFechaDto.getNameTourney(), chargeResultsFechaDto.getYear())
                        .orElseThrow(TourneyNotExistException::new);

        if(fechaTourneyRepository.existsByFechaAndTourney(chargeResultsFechaDto.getFecha(), tourney))
            throw new FechasIsAlreadyLoadedException(chargeResultsFechaDto.getFecha());

        for(ResultDto resultDto : chargeResultsFechaDto.getResults()){
            results.add(Result.mapToResult(resultDto));
        }

        fechaTourneyRepository.save(FechaTourney.mapToFechaTourney(chargeResultsFechaDto, tourney, results));

        response.setTourney(chargeResultsFechaDto.getNameTourney());
        response.setNameUser(FECHA + chargeResultsFechaDto.getFecha());
        response.setFecha(chargeResultsFechaDto.getFecha());
        response.setResults(results.stream()
                        .map(ResultResponse::mapFromResult)
                        .collect(Collectors.toList()));

        return response;
    }
}

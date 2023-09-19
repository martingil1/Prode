package com.example.prode.services;

import com.example.prode.daos.ChargeResultsFechaDao;
import com.example.prode.daos.ResultDao;
import com.example.prode.exceptions.TourneyNotExistException;
import com.example.prode.models.FechaTourney;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import com.example.prode.repositories.FechaTourneyDto;
import com.example.prode.repositories.TourneyDto;
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
    FechaTourneyDto fechaTourneyDto;
    @Autowired
    TourneyDto tourneyDto;

    @Override
    public ChargeResultResponse chargeResultFecha(ChargeResultsFechaDao chargeResultsFechaDao) {

        List<Result> results = new ArrayList<>();
        ChargeResultResponse response = new ChargeResultResponse();

        if(!tourneyDto.existsTourneyByNameAndYearTourney(chargeResultsFechaDao.getNameTourney(), chargeResultsFechaDao.getYear()))
            throw new TourneyNotExistException();

        Tourney tourney =
                tourneyDto.getTourneyByNameAndYearTourney(chargeResultsFechaDao.getNameTourney(), chargeResultsFechaDao.getYear());

        for(ResultDao resultDao : chargeResultsFechaDao.getResults()){
            results.add(Result.mapToResult(resultDao));
        }

        fechaTourneyDto.save(FechaTourney.mapToFechaTourney(chargeResultsFechaDao, tourney, results));

        response.setTourney(chargeResultsFechaDao.getNameTourney());
        response.setFecha(chargeResultsFechaDao.getFecha());
        response.setResults(results.stream()
                        .map(ResultResponse::mapFromResult)
                        .collect(Collectors.toList()));

        return response;
    }
}

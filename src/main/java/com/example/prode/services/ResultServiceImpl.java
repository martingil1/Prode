package com.example.prode.services;

import com.example.prode.daos.ResultDao;
import com.example.prode.daos.TourneyDao;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import com.example.prode.repositories.ResultDto;
import com.example.prode.repositories.TourneyDto;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultDto resultDto;

    @Autowired
    TourneyDto tourneyDto;

    @Override
    public ChargeResultResponse chargeResult(TourneyDao tourneyDao) {

        List<Result> results = new ArrayList<>();

        ChargeResultResponse response = new ChargeResultResponse();

        Tourney tourney = Tourney.mapToTourney(tourneyDao);
        tourneyDto.save(tourney);

        for(ResultDao resultDao : tourneyDao.getResults()){
            results.add(Result.mapToResult(resultDao));
            resultDto.save(Result.mapToResult(resultDao));
        }

        results.forEach(System.out::println);

        response.setTourney(tourney.toString());
        response.setResults(results);

        return response;
    }

    @Override
    public List<Result> getResultByFecha() {

        List<Result> results = (List<Result>) resultDto.findAll();

        results.forEach(System.out::println);

        return results;
    }

    @Override
    public ResultResponse getResultByUser(String user, String fecha) {

        Integer sum = calculateResult(user, fecha);
        ResultResponse aux = new ResultResponse();
        aux.setNombre(user);
        aux.setSumResult(sum);

        return aux;
    }

    private Integer calculateResult(String user, String fecha) {

        List<Result> results = resultDto.getResultByrUser(user);
        List<Result> fechas = resultDto.getResultByrUser(fecha);

        if(fechas.isEmpty()) throw new FechaIsNotChargeException();

        Integer sum = 0;

        for(Result r : results){

            Result aux = fechas.get(results.indexOf(r));
            if(aux.getGolLocalTeam() == r.getGolLocalTeam()
                    && aux.getGolVisitingTeam() == r.getGolVisitingTeam()){
                sum +=3;
            }else if(aux.getGolLocalTeam() > aux.getGolVisitingTeam()
                    && r.getGolLocalTeam() > r.getGolVisitingTeam()){
                sum += 2;
            }else if(aux.getGolLocalTeam() == aux.getGolVisitingTeam()
                        && r.getGolLocalTeam() == r.getGolVisitingTeam()){
                sum += 2;
            }else{
                sum += 0;
            }
        }
        return sum;
    }
}

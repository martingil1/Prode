package com.example.prode.services;

import com.example.prode.daos.ResultDao;
import com.example.prode.daos.ChargeResultsDao;
import com.example.prode.exceptions.TourneyNotExistException;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import com.example.prode.models.User;
import com.example.prode.repositories.ResultDto;
import com.example.prode.repositories.ScoreDto;
import com.example.prode.repositories.TourneyDto;
import com.example.prode.repositories.UserDto;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.SumResultResponse;
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

    @Autowired
    UserDto userDto;

    @Autowired
    ScoreDto scoreDto;

    @Autowired
    ScoreService scoreService;

   @Override
    public ChargeResultResponse chargeResult(ChargeResultsDao chargeResultsDao) {

       List<Result> results = new ArrayList<>();

       if(!tourneyDto.existsTourneyByNameAndYearTourney(chargeResultsDao.getNameTourney(), chargeResultsDao.getYear()))
           throw new TourneyNotExistException();

       Tourney tourney = tourneyDto.getTourneyByNameAndYearTourney(chargeResultsDao.getNameTourney(),
               chargeResultsDao.getYear());

       if(!userDto.existsByNameUserAndTourney(chargeResultsDao.getNameUser(),tourney)){
          userDto.save(User.createUser(chargeResultsDao.getNameUser(),tourney));
       }

       for(ResultDao resultDao : chargeResultsDao.getResults()){
         results.add(resultDto.save(Result.mapToResult(resultDao)));
       }

       scoreService.saveScore(chargeResultsDao, results);

       return ChargeResultResponse.fromResult(chargeResultsDao, results);
    }

    @Override
    public List<Result> getResultByFecha() {

        List<Result> results = (List<Result>) resultDto.findAll();

        results.forEach(System.out::println);

        return results;
    }

    @Override
    public SumResultResponse getResultByUser(ChargeResultsDao chargeResultsDao) {

        Integer sum = calculateResult(chargeResultsDao);
        SumResultResponse aux = new SumResultResponse();
        aux.setNombre(chargeResultsDao.getNameUser());
        aux.setSumResult(sum);

        return aux;
    }

    private Integer calculateResult(ChargeResultsDao chargeResultsDao) {

        /*

        List<Result> results = resultDto.getResultByNameUser(user, tourney, year, fecha);
        List<Result> fechas = resultDto.getResultByNameUser(user, tourney, year, fecha);

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
        }*/
        return 0;
    }
}

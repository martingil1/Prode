package com.example.prode.services;

import com.example.prode.dtos.ResultDto;
import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.exceptions.TourneyNotExistException;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import com.example.prode.models.User;
import com.example.prode.repositories.ResultRepository;
import com.example.prode.repositories.ScoreRepository;
import com.example.prode.repositories.TourneyRepository;
import com.example.prode.repositories.UserRepository;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.SumResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    TourneyRepository tourneyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Autowired
    ScoreService scoreService;

   @Override
    public ChargeResultResponse chargeResult(ChargeResultsDto chargeResultsDto) {

       List<Result> results = new ArrayList<>();

       if(!tourneyRepository.existsTourneyByNameAndYearTourney(chargeResultsDto.getNameTourney(), chargeResultsDto.getYear()))
           throw new TourneyNotExistException();

       Tourney tourney = tourneyRepository.getTourneyByNameAndYearTourney(chargeResultsDto.getNameTourney(),
               chargeResultsDto.getYear());

       if(!userRepository.existsByNameUserAndTourney(chargeResultsDto.getNameUser(),tourney)){
          userRepository.save(User.createUser(chargeResultsDto.getNameUser(),tourney));
       }

       for(ResultDto resultDto : chargeResultsDto.getResults()){
         results.add(resultRepository.save(Result.mapToResult(resultDto)));
       }

       scoreService.saveScore(chargeResultsDto, results);

       return ChargeResultResponse.fromResult(chargeResultsDto, results);
    }

    @Override
    public List<Result> getResultByFecha() {

        List<Result> results = (List<Result>) resultRepository.findAll();

        results.forEach(System.out::println);

        return results;
    }

    @Override
    public SumResultResponse getResultByUser(ChargeResultsDto chargeResultsDto) {

        Integer sum = calculateResult(chargeResultsDto);
        SumResultResponse aux = new SumResultResponse();
        aux.setNombre(chargeResultsDto.getNameUser());
        aux.setSumResult(sum);

        return aux;
    }

    private Integer calculateResult(ChargeResultsDto chargeResultsDto) {

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

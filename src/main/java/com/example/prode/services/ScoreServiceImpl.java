package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.exceptions.UserIsNotExistException;
import com.example.prode.models.Result;
import com.example.prode.models.Score;
import com.example.prode.repositories.FechaTourneyRepository;
import com.example.prode.repositories.ScoreRepository;
import com.example.prode.repositories.UserRepository;
import com.example.prode.responses.SumResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    FechaTourneyRepository fechaTourneyRepository;

    @Autowired
    ScoreRepository scoreRepository;

    @Override
    public void saveScore(ChargeResultsDto chargeResultsDto, List<Result> results) {

        scoreRepository.save(Score.builder()
                .fechaTourney(fechaTourneyRepository.getFechaTourneyByFechaAndTourney(
                                chargeResultsDto.getFecha(),
                                chargeResultsDto.getNameTourney(),
                                chargeResultsDto.getYear()).orElseThrow(FechaIsNotChargeException::new))
                .user(userRepository.getUserByNameUser(chargeResultsDto.getNameUser()))
                .results(results)
                .sumPartialFecha(0L)
                .sumTotalFecha(0L)
                .sumPartialTourney(0L)
                .sumTotalTourney(0L)
                .build());
    }

    @Override
    public SumResultResponse showScorePartialByFechaAndUser(ChargeResultsDto chargeResultsDto) {

        if(!userRepository.existsByNameUser(chargeResultsDto.getNameUser()))
            throw new UserIsNotExistException();

        Integer sum = calculateResult(chargeResultsDto);
        SumResultResponse aux = new SumResultResponse();
        aux.setName(chargeResultsDto.getNameUser());
        aux.setTourney(chargeResultsDto.getNameTourney());
        aux.setYear(chargeResultsDto.getYear());
        aux.setFecha(chargeResultsDto.getFecha());
        aux.setSumResult(sum);

        return aux;
    }

    @Override
    public SumResultResponse showScorePartialByTourneyAndUser(ChargeResultsDto chargeResultsDto) {

        List<Integer> fechas = fechaTourneyRepository.getCantOfFechasByTourney(
                                chargeResultsDto.getNameTourney(), chargeResultsDto.getYear());

        Integer sum = 0;

        for(Integer fecha : fechas){

            chargeResultsDto.setFecha(fecha);
            sum += calculateResult(chargeResultsDto);
        }

        SumResultResponse aux = new SumResultResponse();
        aux.setName(chargeResultsDto.getNameUser());
        aux.setTourney(chargeResultsDto.getNameTourney());
        aux.setYear(chargeResultsDto.getYear());
        aux.setSumResult(sum);

                /*(long) fechas.stream()
                .peek((fecha) -> {
                    chargeResultsDto.setFecha(fecha);
                    calculateResult(chargeResultsDto);
                }).collect(Collectors.toList()).stream()
                        .peek((aux) -> aux + aux).*/

        return aux;
    }


    private Integer calculateResult(ChargeResultsDto chargeResultsDto) {

        List<Result> results = scoreRepository.getResultsByNameUserAndTourney(chargeResultsDto.getNameUser(),
                chargeResultsDto.getNameTourney(),
                chargeResultsDto.getYear(),
                chargeResultsDto.getFecha());
        List<Result> fechas = fechaTourneyRepository.getResultsByTourneyAndFecha(
                chargeResultsDto.getNameTourney(),
                chargeResultsDto.getYear(),
                chargeResultsDto.getFecha()).orElseThrow(FechaIsNotChargeException::new);

        Integer sum = 0;

        for(Result r : results){

            Result aux = fechas.get(results.indexOf(r));
            if(aux.getGolLocalTeam().equals(r.getGolLocalTeam())
                    && aux.getGolVisitingTeam().equals(r.getGolVisitingTeam())){
                sum +=3;
            }else if(aux.getGolLocalTeam() > aux.getGolVisitingTeam()
                    && r.getGolLocalTeam() > r.getGolVisitingTeam()){
                sum += 2;
            }else if(aux.getGolLocalTeam().equals(aux.getGolVisitingTeam())
                    && r.getGolLocalTeam().equals(r.getGolVisitingTeam())){
                sum += 2;
            }else{
                sum += 0;
            }
        }
        return sum;
    }
}

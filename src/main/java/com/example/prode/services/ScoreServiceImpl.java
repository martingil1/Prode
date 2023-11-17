package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.exceptions.ResultsIsNotChargedException;
import com.example.prode.exceptions.UserIsNotExistException;
import com.example.prode.exceptions.UserIsNotInTheTourneyException;
import com.example.prode.models.Result;
import com.example.prode.models.Score;
import com.example.prode.repositories.FechaTourneyRepository;
import com.example.prode.repositories.ScoreRepository;
import com.example.prode.repositories.UserRepository;
import com.example.prode.responses.PositionsResponse;
import com.example.prode.responses.SumResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

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
                .user(userRepository.getUserByNameUserAndTourney(chargeResultsDto.getNameUser(),
                        chargeResultsDto.getNameTourney(),
                        chargeResultsDto.getYear()).get())
                .results(results)
                .sumPartialFecha(0L)
                .sumTotalFecha(0L)
                .sumPartialTourney(0L)
                .sumTotalTourney(0L)
                .build());
    }

    @Override
    public PositionsResponse showPositionsByFecha(ChargeResultsDto chargeResultsDto) {

        Map<String, Integer> positions = new TreeMap<>();
        SumResultResponse sumResultResponse;

        List<String> users = scoreRepository.getUsersByTourneyAndFecha(
                chargeResultsDto.getNameTourney(),
                chargeResultsDto.getYear(),
                chargeResultsDto.getFecha());

        for(String userName : users){
            chargeResultsDto.setNameUser(userName);
            sumResultResponse = showScorePartialByFechaAndUser(chargeResultsDto);
            positions.put(sumResultResponse.getName(), sumResultResponse.getSumResult());
        }

        return PositionsResponse.builder()
                .tourney(chargeResultsDto.getNameTourney())
                .year(chargeResultsDto.getYear())
                .fecha(chargeResultsDto.getFecha())
                .users(positions.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> newValue,
                                LinkedHashMap::new
                        )))
                .build();
    }

    @Override
    public PositionsResponse showPositionsByTourney(ChargeResultsDto chargeResultsDto) {

        Map<String, Integer> positions = new TreeMap<>();
        SumResultResponse sumResultResponse;

        List<String> users = scoreRepository.getUsersByTourney(
                chargeResultsDto.getNameTourney(),
                chargeResultsDto.getYear());

        List<Integer> fechas = fechaTourneyRepository.getCantOfFechasByTourney(
                chargeResultsDto.getNameTourney(), chargeResultsDto.getYear());

        for (Integer fecha : fechas) {

            for(String userName : users){
                chargeResultsDto.setFecha(fecha);
                chargeResultsDto.setNameUser(userName);
                sumResultResponse = showScorePartialByTourneyAndUser(chargeResultsDto);
                positions.put(sumResultResponse.getName(), sumResultResponse.getSumResult());
            }

        }
        return PositionsResponse.builder()
                .tourney(chargeResultsDto.getNameTourney())
                .year(chargeResultsDto.getYear())
                .users(positions.entrySet().stream()
                        .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                        .collect(Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (oldValue, newValue) -> newValue,
                                LinkedHashMap::new
                        )))
                .build();
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

        if(results.isEmpty())
            throw new ResultsIsNotChargedException(chargeResultsDto.getNameUser(),chargeResultsDto.getFecha());

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

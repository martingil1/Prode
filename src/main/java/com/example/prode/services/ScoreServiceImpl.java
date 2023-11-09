package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.models.Result;
import com.example.prode.models.Score;
import com.example.prode.repositories.FechaTourneyRepository;
import com.example.prode.repositories.ScoreRepository;
import com.example.prode.repositories.UserRepository;
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
}

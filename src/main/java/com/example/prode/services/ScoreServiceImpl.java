package com.example.prode.services;

import com.example.prode.daos.ChargeResultsDao;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.models.Result;
import com.example.prode.models.Score;
import com.example.prode.repositories.FechaTourneyDto;
import com.example.prode.repositories.ScoreDto;
import com.example.prode.repositories.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService{

    @Autowired
    UserDto userDto;

    @Autowired
    FechaTourneyDto fechaTourneyDto;

    @Autowired
    ScoreDto scoreDto;

    @Override
    public void saveScore(ChargeResultsDao chargeResultsDao, List<Result> results) {

        scoreDto.save(Score.builder()
                .fechaTourney(fechaTourneyDto.getFechaTourneyByFechaAndTourney(
                                chargeResultsDao.getFecha(),
                                chargeResultsDao.getNameTourney(),
                                chargeResultsDao.getYear()).orElseThrow(FechaIsNotChargeException::new))
                .user(userDto.getUserByNameUser(chargeResultsDao.getNameUser()))
                .results(results)
                .sumPartialFecha(0L)
                .sumTotalFecha(0L)
                .sumPartialTourney(0L)
                .sumTotalTourney(0L)
                .build());
    }
}

package com.example.prode.services;

import com.example.prode.dtos.ResultDto;
import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.exceptions.TourneyNotExistException;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import com.example.prode.models.User;
import com.example.prode.repositories.FechaTourneyRepository;
import com.example.prode.repositories.ResultRepository;
import com.example.prode.repositories.ScoreRepository;
import com.example.prode.repositories.TourneyRepository;
import com.example.prode.repositories.UserRepository;
import com.example.prode.responses.ChargeResultResponse;
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

    @Autowired
    FechaTourneyRepository fechaTourneyRepository;

   @Override
    public ChargeResultResponse chargeResult(ChargeResultsDto chargeResultsDto) {

       List<Result> results = new ArrayList<>();

       Tourney tourney = tourneyRepository.getTourneyByNameAndYearTourney(chargeResultsDto.getNameTourney(),
               chargeResultsDto.getYear()).orElseThrow(TourneyNotExistException::new);

       if(!userRepository.existsByNameUserAndTourney(chargeResultsDto.getNameUser(),tourney)){
          userRepository.save(User.createUser(chargeResultsDto.getNameUser(),tourney));
       }

       for(ResultDto resultDto : chargeResultsDto.getResults()){
         results.add(resultRepository.save(Result.mapToResult(resultDto)));
       }

       scoreService.saveScore(chargeResultsDto, results);

       return ChargeResultResponse.fromResult(chargeResultsDto, results);
    }


}

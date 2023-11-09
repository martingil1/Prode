package com.example.prode.services;

import com.example.prode.dtos.TourneyDto;
import com.example.prode.models.Tourney;
import com.example.prode.repositories.TourneyRepository;
import com.example.prode.responses.TourneyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourneyServiceImpl implements TourneyService{

    @Autowired
    TourneyRepository tourneyRepository;
    @Override
    public TourneyResponse chargeTourney(TourneyDto tourneyDto) {

        return TourneyResponse.fromTourney(tourneyRepository.save(Tourney.fromTourneyDao(tourneyDto)));
    }
}

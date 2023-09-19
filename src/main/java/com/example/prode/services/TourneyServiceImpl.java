package com.example.prode.services;

import com.example.prode.daos.TourneyDao;
import com.example.prode.models.Tourney;
import com.example.prode.repositories.TourneyDto;
import com.example.prode.responses.TourneyResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TourneyServiceImpl implements TourneyService{

    @Autowired
    TourneyDto tourneyDto;
    @Override
    public TourneyResponse chargeTourney(TourneyDao tourneyDao) {

        return TourneyResponse.fromTourney(tourneyDto.save(Tourney.fromTourneyDao(tourneyDao)));
    }
}

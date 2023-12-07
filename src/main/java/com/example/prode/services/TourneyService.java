package com.example.prode.services;

import com.example.prode.dtos.TourneyDto;
import com.example.prode.responses.TourneyResponse;

public interface TourneyService {

    TourneyResponse chargeTourney(TourneyDto tourneyDto);

    void deleteTourney(String tourney, Long year);
}

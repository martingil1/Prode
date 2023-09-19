package com.example.prode.services;

import com.example.prode.daos.TourneyDao;
import com.example.prode.responses.TourneyResponse;

public interface TourneyService {

    TourneyResponse chargeTourney(TourneyDao tourneyDao);

}

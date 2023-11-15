package com.example.prode.repositories;

import com.example.prode.models.Tourney;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TourneyRepository extends CrudRepository<Tourney, String> {

    //Boolean existsTourneyByNameAndYearTourney(String name, Long yearTourney);

    Optional<Tourney> getTourneyByNameAndYearTourney(String name, Long yearTourney);

}

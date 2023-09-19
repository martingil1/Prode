package com.example.prode.repositories;

import com.example.prode.models.FechaTourney;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FechaTourneyDto extends CrudRepository<FechaTourney, Long> {

    @Query("SELECT FT FROM FechaTourney FT " +
            "INNER JOIN Tourney T ON FT.tourney.name = T.name AND FT.tourney.yearTourney = T.yearTourney " +
            "WHERE FT.tourney.name = :nameTourney AND FT.tourney.yearTourney = :year AND FT.fecha = :fecha")
    Optional<FechaTourney> getFechaTourneyByFechaAndTourney(Integer fecha, String nameTourney, Long year);

}

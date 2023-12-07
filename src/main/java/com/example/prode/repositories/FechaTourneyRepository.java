package com.example.prode.repositories;

import com.example.prode.models.FechaTourney;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FechaTourneyRepository extends CrudRepository<FechaTourney, Long> {

    @Query("SELECT FT FROM FechaTourney FT " +
            "INNER JOIN Tourney T ON FT.tourney.name = T.name AND FT.tourney.yearTourney = T.yearTourney " +
            "WHERE FT.tourney.name = :nameTourney AND FT.tourney.yearTourney = :year AND FT.fecha = :fecha")
    Optional<FechaTourney> getFechaTourneyByFechaAndTourney(Integer fecha, String nameTourney, Long year);


    @Query("SELECT FT.results FROM FechaTourney FT " +
            " WHERE FT.tourney.name = :tourney " +
            " AND FT.tourney.yearTourney = :year AND FT.fecha = :fecha")
    Optional<List<Result>> getResultsByTourneyAndFecha(String tourney, Long year, Integer fecha);

    @Query("SELECT FT.fecha FROM FechaTourney FT " +
            "INNER JOIN Tourney T ON FT.tourney.name = T.name AND FT.tourney.yearTourney = T.yearTourney " +
            "WHERE FT.tourney.name = :nameTourney AND FT.tourney.yearTourney = :year")
    List<Integer> getCantOfFechasByTourney(String nameTourney, Long year);

    Boolean existsByFechaAndTourney(Integer fecha, Tourney tourney);

    FechaTourney getFechaTourneysById(Long id);

}

package com.example.prode.repositories;

import com.example.prode.models.Result;
import com.example.prode.models.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {

    @Query("SELECT S.results FROM Score S " +
            " WHERE S.user.nameUser = :user AND S.fechaTourney.tourney.name = :tourney " +
            " AND S.fechaTourney.tourney.yearTourney = :year AND S.fechaTourney.fecha = :fecha")
    List<Result> getResultsByNameUserAndTourney(String user, String tourney, Long year, Integer fecha);

    @Query("SELECT S.user.nameUser FROM Score S" +
            " WHERE S.fechaTourney.tourney.name = :tourney" +
            " AND S.fechaTourney.tourney.yearTourney = :year AND S.fechaTourney.fecha = :fecha")
    List<String> getUsersByTourneyAndFecha(String tourney, Long year, Integer fecha);

    @Query("SELECT S.user.nameUser FROM Score S" +
            " WHERE S.fechaTourney.tourney.name = :tourney" +
            " AND S.fechaTourney.tourney.yearTourney = :year")
    List<String> getUsersByTourney(String tourney, Long year);

}

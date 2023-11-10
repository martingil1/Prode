package com.example.prode.repositories;

import com.example.prode.models.Result;
import com.example.prode.models.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreRepository extends CrudRepository<Score, Long> {

    //@Query(value = "SELECT * FROM SCORE_RESULT SR WHERE SR.SCORE_ID = :id", nativeQuery = true)
    //List<Score> getScorePartialByUser (String userName);

    @Query("SELECT S.results FROM Score S " +
            " WHERE S.user.nameUser = :user AND S.fechaTourney.tourney.name = :tourney " +
            " AND S.fechaTourney.tourney.yearTourney = :year AND S.fechaTourney.fecha = :fecha")
    List<Result> getResultsByNameUserAndTourney(String user, String tourney, Long year, Integer fecha);

}

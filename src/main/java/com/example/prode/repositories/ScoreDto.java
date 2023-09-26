package com.example.prode.repositories;

import com.example.prode.models.Score;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScoreDto extends CrudRepository<Score, Long> {

    @Query(value = "SELECT * FROM SCORE_RESULT SR WHERE SR.SCORE_ID = :id", nativeQuery = true)
    List<Score> getScorePartialByUser (String userName);

}

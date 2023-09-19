package com.example.prode.repositories;

import com.example.prode.models.Score;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreDto extends CrudRepository<Score, Long> {
}

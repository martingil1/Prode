package com.example.prode.repositories;

import com.example.prode.models.Tourney;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TourneyDto extends CrudRepository<Tourney, String> {
}

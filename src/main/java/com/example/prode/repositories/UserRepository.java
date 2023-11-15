package com.example.prode.repositories;

import com.example.prode.models.Tourney;
import com.example.prode.models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Boolean existsByNameUser(String nameUser);

    Boolean existsByNameUserAndTourney(String nameUser, Tourney tourney);

    User getUserByNameUser(String nameUser);

    Optional<User> getUserByNameUserAndTourney(String nameUser, Tourney tourney);

}

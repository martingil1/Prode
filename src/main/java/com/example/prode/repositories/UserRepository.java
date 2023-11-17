package com.example.prode.repositories;

import com.example.prode.models.Tourney;
import com.example.prode.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    Boolean existsByNameUser(String nameUser);

    Boolean existsByNameUserAndTourney(String nameUser, Tourney tourney);

    User getUserByNameUser(String nameUser);

    @Query("SELECT U FROM User U WHERE U.nameUser = :nameUser" +
            " AND U.tourney.name = :nameTourney AND U.tourney.yearTourney = :yearTourney")
    Optional<User> getUserByNameUserAndTourney(String nameUser, String nameTourney, Long yearTourney);

}

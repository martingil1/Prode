package com.example.prode.repositories;

import com.example.prode.models.Tourney;
import com.example.prode.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDto extends CrudRepository<User, Long> {

    Boolean existsByNameUser(String nameUser);

    /*@Query("SELECT U FROM User U " +
            "INNER JOIN Tourney T ON U.tourney.name = T.name AND U.tourney.yearTourney = T.yearTourney " +
            "WHERE U.tourney.name = :nameTourney AND U.tourney.yearTourney = :year AND U.nameUser = :nameUser")
    */
    Boolean existsByNameUserAndTourney(String nameUser, Tourney tourney);
    User getUserByNameUser(String nameUser);

}

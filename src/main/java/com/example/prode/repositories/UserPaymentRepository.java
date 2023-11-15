package com.example.prode.repositories;

import com.example.prode.models.UserPayment;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserPaymentRepository extends CrudRepository<UserPayment, Long> {

    @Query("SELECT UP FROM UserPayment UP " +
            " WHERE UP.user.nameUser = :nameUser AND UP.tourney.name = :tourneyName" +
            " AND UP.tourney.yearTourney = :yearTourney AND UP.paymentTourney = true AND UP.fechaTourney.fecha = 1")
    UserPayment findUserPaymentWithTourneyPayment(String nameUser, String tourneyName, Long yearTourney);

    @Query("SELECT UP FROM UserPayment UP " +
            " WHERE UP.user.nameUser = :nameUser AND UP.tourney.name = :tourneyName" +
            " AND UP.tourney.yearTourney = :yearTourney AND UP.fechaTourney.fecha = :fecha")
    Optional<UserPayment> findUserPaymentByTourneyAndFechaTourney(String nameUser, String tourneyName, Long yearTourney, Integer fecha);

}

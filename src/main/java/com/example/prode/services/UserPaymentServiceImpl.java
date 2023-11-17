package com.example.prode.services;

import com.example.prode.dtos.PaymentDto;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.exceptions.TourneyNotExistException;
import com.example.prode.exceptions.UserIsNotInTheTourneyException;
import com.example.prode.models.FechaTourney;
import com.example.prode.models.Tourney;
import com.example.prode.models.User;
import com.example.prode.models.UserPayment;
import com.example.prode.repositories.FechaTourneyRepository;
import com.example.prode.repositories.TourneyRepository;
import com.example.prode.repositories.UserPaymentRepository;
import com.example.prode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPaymentServiceImpl implements UserPaymentService{

    @Autowired
    TourneyRepository tourneyRepository;

    @Autowired
    FechaTourneyRepository fechaTourneyRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserPaymentRepository userPaymentRepository;

    @Override
    public void chargePaymentTourneyOfUser(PaymentDto paymentDto) {

        if(userPaymentRepository.findUserPaymentWithTourneyPayment(paymentDto.getUserName(),
                paymentDto.getNameTourney(),
                paymentDto.getYearTourney()) == null){

            UserPayment userPayment = getUserPayment(paymentDto);

            userPayment.setPaymentTourney(true);
            userPaymentRepository.save(userPayment);

        }
    }

    @Override
    public void chargePaymentFechaTourneyOfUser(PaymentDto paymentDto) {

        UserPayment userPayment = getUserPayment(paymentDto);

        userPayment.setPaymentFechaTourney(true);
        userPaymentRepository.save(userPayment);

    }

    private UserPayment getUserPayment(PaymentDto paymentDto){

        return userPaymentRepository
                .findUserPaymentByTourneyAndFechaTourney(paymentDto.getUserName(),
                        paymentDto.getNameTourney(),
                        paymentDto.getYearTourney(),
                        paymentDto.getFechaTourney())
                .orElse(buildUserPayment(paymentDto.getUserName(),
                        paymentDto.getNameTourney(),
                        paymentDto.getYearTourney(),
                        paymentDto.getFechaTourney()));
    }

    private UserPayment buildUserPayment(String userName, String nameTourney, Long tourneyYear, Integer fechaTourney){

        Boolean flag = false;

        if(userPaymentRepository.findUserPaymentWithTourneyPayment(userName, nameTourney, tourneyYear) != null)
            flag = true;

        Tourney tourney = tourneyRepository.getTourneyByNameAndYearTourney(nameTourney, tourneyYear)
                .orElseThrow(TourneyNotExistException::new);

        User user = userRepository.getUserByNameUserAndTourney(userName, tourney.getName(), tourney.getYearTourney())
                .orElseThrow(UserIsNotInTheTourneyException::new);

        FechaTourney fechaTourney1 = fechaTourneyRepository.getFechaTourneyByFechaAndTourney(fechaTourney, nameTourney, tourneyYear)
                .orElseThrow(FechaIsNotChargeException::new);

        return UserPayment.createObject(user, tourney, fechaTourney1, flag);
    }
}

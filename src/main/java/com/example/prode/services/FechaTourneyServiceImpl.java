package com.example.prode.services;

import com.example.prode.dtos.ChargeResultsFechaDto;
import com.example.prode.dtos.ResultDto;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.exceptions.FechasIsAlreadyLoadedException;
import com.example.prode.exceptions.TourneyNotExistException;
import com.example.prode.models.FechaTourney;
import com.example.prode.models.Result;
import com.example.prode.models.Tourney;
import com.example.prode.repositories.FechaTourneyRepository;
import com.example.prode.repositories.TourneyRepository;
import com.example.prode.responses.ChargeResultResponse;
import com.example.prode.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.example.prode.utils.Constants.FECHA;

@Service
public class FechaTourneyServiceImpl implements FechaTourneyService{

    @Autowired
    FechaTourneyRepository fechaTourneyRepository;
    @Autowired
    TourneyRepository tourneyRepository;

    @Override
    public ChargeResultResponse chargeFechaEmpty(ChargeResultsFechaDto chargeResultsFechaDto) {

        List<Result> results = new ArrayList<>();
        Tourney tourney =
                tourneyRepository.getTourneyByNameAndYearTourney(
                                chargeResultsFechaDto.getNameTourney(), chargeResultsFechaDto.getYear())
                        .orElseThrow(TourneyNotExistException::new);

       if(fechaTourneyRepository.existsByFechaAndTourney(chargeResultsFechaDto.getFecha(), tourney))
         throw new FechasIsAlreadyLoadedException(chargeResultsFechaDto.getFecha());

        for(ResultDto resultDto : chargeResultsFechaDto.getResults()){
            results.add(Result.mapToResult(resultDto));
        }

        fechaTourneyRepository.save(FechaTourney.mapToFechaTourney(
                chargeResultsFechaDto.getFecha(), tourney, results));

        return ChargeResultResponse.builder()
                .tourney(chargeResultsFechaDto.getNameTourney())
                .nameUser(FECHA + chargeResultsFechaDto.getFecha())
                .fecha(chargeResultsFechaDto.getFecha())
                .results(results.stream()
                        .map(ResultResponse::mapFromResult)
                        .collect(Collectors.toList()))
                .build();
    }

    @Override
    public void deleteFechaTourney(Integer fecha, String tourney, Long year) {

        FechaTourney fechaTourney = fechaTourneyRepository.getFechaTourneyByFechaAndTourney(fecha, tourney, year)
                .orElseThrow(FechaIsNotChargeException::new);

        fechaTourneyRepository.delete(fechaTourney);
    }

    @Override
    public ChargeResultResponse chargeResultFecha(ChargeResultsFechaDto chargeResultsFechaDto) {

        List<Result> results = new ArrayList<>();

        FechaTourney fechaTourney = fechaTourneyRepository.getFechaTourneyByFechaAndTourney(
                chargeResultsFechaDto.getFecha(),
                chargeResultsFechaDto.getNameTourney(),
                chargeResultsFechaDto.getYear()).orElseThrow(FechaIsNotChargeException::new);

        results = modifiedResultsEmpties(chargeResultsFechaDto);

        FechaTourney fechaTourneyWithResults =  fechaTourneyRepository.getFechaTourneysById(fechaTourney.getId());
        fechaTourneyWithResults.setResults(results);
        fechaTourneyRepository.save(fechaTourneyWithResults);

        return ChargeResultResponse.builder()
                .tourney(chargeResultsFechaDto.getNameTourney())
                .nameUser(FECHA + chargeResultsFechaDto.getFecha())
                .fecha(chargeResultsFechaDto.getFecha())
                .results(results.stream()
                        .map(ResultResponse::mapFromResult)
                        .collect(Collectors.toList()))
                .build();
    }


    private List<Result> modifiedResultsEmpties(ChargeResultsFechaDto chargeResultsFechaDto){

        List<Result> results = new ArrayList<>();

        List<Result> resultsCharges = fechaTourneyRepository.getResultsByTourneyAndFecha(
                chargeResultsFechaDto.getNameTourney(),
                chargeResultsFechaDto.getYear(),
                chargeResultsFechaDto.getFecha())
                .orElseThrow(FechaIsNotChargeException::new);

        for(Result result : resultsCharges){

            if(Objects.isNull(result.getGolLocalTeam())){

                for(ResultDto resultDto : chargeResultsFechaDto.getResults()){

                    if(resultDto.getLocalTeam().equals(result.getLocalTeam())
                            && resultDto.getVisitingTeam().equals(result.getVisitingTeam())){

                        result.setGolLocalTeam(resultDto.getGolLocalTeam());
                        result.setGolVisitingTeam(resultDto.getGolVisitingTeam());
                        results.add(result);
                        break;
                    }
                }
            }
            if (!results.contains(result)) {
                results.add(result);
            }
        }
        return results;
    }

}

package com.example.prode.services;

import com.example.prode.daos.ResultDao;
import com.example.prode.exceptions.FechaIsNotChargeException;
import com.example.prode.models.Result;
import com.example.prode.repositories.ResultDto;
import com.example.prode.responses.ResultResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    ResultDto resultDto;

    @Override
    public void chargeResult(List<ResultDao> resultadosDao) {

        List<Result> results = new ArrayList<>();

        for(ResultDao resultDao : resultadosDao){
            results.add(Result.mapToResultado(resultDao));
        }

        results.forEach(System.out::println);

    }

    @Override
    public List<Result> getResultByFecha() {

        List<Result> results = (List<Result>) resultDto.findAll();

        results.forEach(System.out::println);

        return results;
    }

    @Override
    public ResultResponse getResultByUser(String user, String fecha) {

        Integer sum = calculateResult(user, fecha);
        ResultResponse aux = new ResultResponse();
        aux.setNombre(user);
        aux.setSumaResultado(sum);

        return aux;
    }

    private Integer calculateResult(String user, String fecha) {

        List<Result> results = resultDto.getResultByrUser(user);
        List<Result> fechas = resultDto.getResultByrUser(fecha);

        if(fechas.isEmpty()) throw new FechaIsNotChargeException();

        Integer sum = 0;

        for(Result r : results){

            Result aux = fechas.get(results.indexOf(r));
            if(aux.getGolLocalTeam() == r.getGolLocalTeam()
                    && aux.getGolVisitingTeam() == r.getGolVisitingTeam()){
                sum +=3;
            }else if(aux.getGolLocalTeam() > aux.getGolVisitingTeam()
                    && r.getGolLocalTeam() > r.getGolVisitingTeam()){
                sum += 2;
            }else if(aux.getGolLocalTeam() == aux.getGolVisitingTeam()
                        && r.getGolLocalTeam() == r.getGolVisitingTeam()){
                sum += 2;
            }else{
                sum += 0;
            }
        }
        return sum;
    }
}

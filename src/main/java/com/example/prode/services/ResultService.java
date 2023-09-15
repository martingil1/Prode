package com.example.prode.services;

import com.example.prode.daos.ResultDao;
import com.example.prode.models.Result;
import com.example.prode.responses.ResultResponse;

import java.util.List;

public interface ResultService {

    void chargeResult(List<ResultDao> results);

    List<Result> getResultByFecha();
    ResultResponse getResultByUser(String user, String fecha);

}

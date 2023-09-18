package com.example.prode.responses;

import com.example.prode.models.Result;
import lombok.Data;

import java.util.List;

@Data
public class ChargeResultResponse {

    public String tourney;

    public List<Result> results;

}

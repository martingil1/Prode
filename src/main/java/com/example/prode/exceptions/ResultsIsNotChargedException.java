package com.example.prode.exceptions;

public class ResultsIsNotChargedException extends RuntimeException{

    public ResultsIsNotChargedException(String user, Integer fecha) {
        super("No hay resultados cargados del usuario " + user +" para la fecha "+fecha);
    }
}

package com.example.prode.exceptions;

public class FechaIsNotChargeException extends RuntimeException{

    public FechaIsNotChargeException(){super("La fecha solicitada aun no esta cargada");}
}

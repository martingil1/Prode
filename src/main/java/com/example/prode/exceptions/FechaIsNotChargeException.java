package com.example.prode.exceptions;

import java.util.function.Supplier;

public class FechaIsNotChargeException extends RuntimeException{

    public FechaIsNotChargeException(){super("La fecha solicitada aun no esta cargada");}
}

package com.example.prode.exceptions;

public class FechasIsAlreadyLoadedException extends RuntimeException{

    public FechasIsAlreadyLoadedException(Integer fecha){super("La fecha "+ fecha +" ya esta cargada");}

}

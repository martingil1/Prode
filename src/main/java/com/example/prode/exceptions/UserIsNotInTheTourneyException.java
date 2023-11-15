package com.example.prode.exceptions;

public class UserIsNotInTheTourneyException extends RuntimeException{

    public UserIsNotInTheTourneyException(){super("El usuario no participa de este torneo");}
}

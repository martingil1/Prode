package com.example.prode.exceptions;

public class UserIsNotExistException extends RuntimeException{

    public UserIsNotExistException(){super("No existe el usuario");}

}

package com.example.prode.responses;

public class ResultResponse {

    public String nombre;

    public Integer sumaResultado;

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setSumaResultado(Integer sumaResultado) {
        this.sumaResultado = sumaResultado;
    }

    @Override
    public String toString() {
        return "Nombre =" + nombre +  " resultado: "+ sumaResultado;
    }
}

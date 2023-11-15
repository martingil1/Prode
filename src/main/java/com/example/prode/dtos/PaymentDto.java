package com.example.prode.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class PaymentDto {

    @JsonProperty("nombre_usuario")
    String userName;
    @JsonProperty("nombre_torneo")
    String nameTourney;
    @JsonProperty("año_torneo")
    Long yearTourney;
    @JsonProperty("fecha_torneo")
    Integer fechaTourney;
    @JsonProperty("abona_torneo")
    Boolean paymentTourney;
    @JsonProperty("abona_fecha")
    Boolean paymentFecha;

}

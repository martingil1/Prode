package com.example.prode.models;

import com.example.prode.dtos.ChargeResultsDto;
import com.example.prode.dtos.TourneyDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TOURNEY")
@IdClass(TourneyId.class)
public class Tourney{

    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "year_tourney")
    private Long yearTourney;

    public static Tourney mapToTourney(ChargeResultsDto chargeResultsDto){

        return Tourney.builder()
                .name(chargeResultsDto.getNameUser())
                .yearTourney(chargeResultsDto.getYear())
                .build();
    }

    public static Tourney fromTourneyDao(TourneyDto tourneyDto){

        return Tourney.builder()
                .name(tourneyDto.getName())
                .yearTourney(tourneyDto.getYear())
                .build();

    }

}

package com.example.prode.models;

import com.example.prode.daos.ChargeResultsDao;
import com.example.prode.daos.TourneyDao;
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

    public static Tourney mapToTourney(ChargeResultsDao chargeResultsDao){

        return Tourney.builder()
                .name(chargeResultsDao.getNameUser())
                .yearTourney(chargeResultsDao.getYear())
                .build();
    }

    public static Tourney fromTourneyDao(TourneyDao tourneyDao){

        return Tourney.builder()
                .name(tourneyDao.getName())
                .yearTourney(tourneyDao.getYear())
                .build();

    }

}

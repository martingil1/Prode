package com.example.prode.models;

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
@Table(name = "tourney")
@IdClass(TourneyId.class)
public class Tourney{

    @Id
    @Column(name = "name")
    private String name;

    @Id
    @Column(name = "year_tourney")
    private Long yearTourney;

    @Column(name = "fecha")
    private Integer fecha;

    public static Tourney mapToTourney(TourneyDao tourneyDao){

        return Tourney.builder()
                .name(tourneyDao.getName())
                .yearTourney(tourneyDao.getYear())
                .fecha(tourneyDao.getFecha())
                .build();
    }

}

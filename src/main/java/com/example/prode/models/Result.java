package com.example.prode.models;

import com.example.prode.daos.ResultDao;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Result")
public class Result {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "local_team")
    private String localTeam;
    @Column(name = "visiting_team")

    private String visitingTeam;
    @Column(name = "gol_local_team")
    private Integer golLocalTeam;
    @Column(name = "gol_visiting_team")
    private Integer golVisitingTeam;

    public static Result mapToResult(ResultDao resultDao){

        return Result.builder()
                .name(resultDao.getName())
                .localTeam(resultDao.getLocalTeam())
                .visitingTeam(resultDao.getVisitingTeam())
                .golLocalTeam(resultDao.getGolLocalTeam())
                .golVisitingTeam(resultDao.getGolVisitingTeam())
                .build();
    }

    @Override
    public String toString() {
        return "Resultados: " +
                " nombre= " + name +
                " equipo Local= " + localTeam +
                " equipo Visitante= " + visitingTeam +
                " gol Equipo Local= " + golLocalTeam +
                " gol Equipo Visitante= " + golVisitingTeam;
    }
}


package com.example.prode.models;

import com.example.prode.dtos.ChargeResultsFechaDto;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "FECHA_TOURNEY")
public class FechaTourney {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_fecha_tourney")
    private Long id;
    @Column()
    private Integer fecha;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "tourney_name", referencedColumnName = "name"),
            @JoinColumn(name = "tourney_year", referencedColumnName = "year_tourney")
    })
    private Tourney tourney;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Result> results;

    public static FechaTourney mapToFechaTourney(ChargeResultsFechaDto resultDao,
                                                 Tourney tourney,
                                                 List<Result> results){

        return FechaTourney.builder()
                .tourney(tourney)
                .fecha(resultDao.getFecha())
                .results(results)
                .build();
    }

}

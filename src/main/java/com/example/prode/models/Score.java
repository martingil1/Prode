package com.example.prode.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "SCORE")
public class Score {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "fecha_tourney")
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private FechaTourney fechaTourney;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private User user;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Result> results;
    @Column(name = "sum_partial_fecha")
    private Long sumPartialFecha;
    @Column(name = "sum_total_fecha")
    private Long sumTotalFecha;
    @Column(name = "sum_partial_tourney")
    private Long sumPartialTourney;
    @Column(name = "sum_total_tourney")
    private Long sumTotalTourney;

}

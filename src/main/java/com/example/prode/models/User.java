package com.example.prode.models;

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
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "USER_PRODE")
public class User {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id_user")
    private Long id;

    @Column(name = "name_user")
    private String nameUser;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumns({
            @JoinColumn(name = "name_tourney", referencedColumnName = "name"),
            @JoinColumn(name = "year_tourney", referencedColumnName = "year_tourney")
    })
    private Tourney tourney;

    public static User createUser(String nameUserParam, Tourney tourneyParam){

        return User.builder()
                .nameUser(nameUserParam)
                .tourney(tourneyParam)
                .build();

    }

}

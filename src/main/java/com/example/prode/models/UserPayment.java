package com.example.prode.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Builder
@Table(name = "USER_PAYMENT")
public class UserPayment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumns({
            @JoinColumn(name = "name"),
            @JoinColumn(name = "year_tourney")
    })
    private Tourney tourney;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_fecha_tourney")
    private FechaTourney fechaTourney;

    @Column(name = "payment_tourney")
    private Boolean paymentTourney;

    @Column(name = "payment_fecha_tourney")
    private Boolean paymentFechaTourney;

    public static UserPayment createObject(User user, Tourney tourney, FechaTourney fechaTourney, Boolean flag){

        return UserPayment.builder()
                .user(user)
                .tourney(tourney)
                .fechaTourney(fechaTourney)
                .paymentTourney(flag)
                .paymentFechaTourney(false)
                .build();
    }

}

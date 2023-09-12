package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class OrderDecorator extends Tour {

    public OrderDecorator(Tour decoratedTour) {
        this.decoratedTour = decoratedTour;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "decorated_tour_id")
    protected Tour decoratedTour;

    public String getDescription() {
        return "Mountain trip with a guide" + decoratedTour.getDescription();
    }

    public double getCost() {
        return 500 + decoratedTour.getCost();
    }
}

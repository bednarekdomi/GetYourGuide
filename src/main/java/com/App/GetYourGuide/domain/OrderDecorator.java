package com.App.GetYourGuide.domain;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class OrderDecorator implements Tour {

    public OrderDecorator(Tour decoratedTour) {
        this.decoratedTour = decoratedTour;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    protected Tour decoratedTour;

    public String getDescription() {
        return decoratedTour.getDescription();
    }

    public double getCost() {
        return decoratedTour.getCost();
    }
}

package com.App.GetYourGuide.domain;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class TourOrderDecorator implements TourOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private final TourOrder tourOrder;

    protected TourOrderDecorator(TourOrder tourOrder) {
        this.tourOrder = tourOrder;
    }

    @Override
    public String getDescription() {
        return tourOrder.getDescription();
    }

    @Override
    public double getCost() {
        return tourOrder.getCost();
    }
}

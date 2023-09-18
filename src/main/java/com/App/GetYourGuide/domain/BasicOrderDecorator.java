package com.App.GetYourGuide.domain;

import jakarta.persistence.*;

@Entity
public class BasicOrderDecorator implements TourOrder {

    @Override
    public String getDescription() {
        return "Mountain trip with a guide";
    }

    @Override
    public double getCost() {
        return 500;
    }
}

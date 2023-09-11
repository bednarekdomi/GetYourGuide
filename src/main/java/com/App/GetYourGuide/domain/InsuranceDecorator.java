package com.App.GetYourGuide.domain;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class InsuranceDecorator extends OrderDecorator {
    public InsuranceDecorator(Tour decoratedTour){
        super(decoratedTour);
    }

    @Override
    public String getDescription() {
        return decoratedTour.getDescription() + ", with mountaineering insurance";
    }

    @Override
    public double getCost() {
        return decoratedTour.getCost() + 100;
    }
}

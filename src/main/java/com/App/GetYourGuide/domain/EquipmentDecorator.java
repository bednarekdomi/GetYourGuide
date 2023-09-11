package com.App.GetYourGuide.domain;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class EquipmentDecorator extends OrderDecorator {

    public EquipmentDecorator(Tour decoratedTour) {
        super(decoratedTour);
    }

    @Override
    public String getDescription() {
        return super.getDescription() + ", with equipment rental";
    }

    @Override
    public double getCost() {
        return super.getCost() + 50;
    }
}

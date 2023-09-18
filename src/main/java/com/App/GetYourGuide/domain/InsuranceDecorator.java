package com.App.GetYourGuide.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
public class InsuranceDecorator extends TourOrderDecorator {

    public InsuranceDecorator(TourOrder tourOrder) {
        super(tourOrder);
    }


    @Override
    public String getDescription() {
        return super.getDescription() + ", with mountaineering insurance";
    }

    @Override
    public double getCost() {
        return super.getCost() + 100;
    }
}

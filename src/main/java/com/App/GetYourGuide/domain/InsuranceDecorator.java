package com.App.GetYourGuide.domain;

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

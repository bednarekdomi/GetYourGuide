package com.App.GetYourGuide.domain;

public class EquipmentDecorator extends TourOrderDecorator {

    public EquipmentDecorator(TourOrder tourOrder) {
        super(tourOrder);
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

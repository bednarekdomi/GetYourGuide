package com.App.GetYourGuide.domain;

public abstract class TourOrderDecorator implements TourOrder {

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

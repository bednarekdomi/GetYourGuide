package com.App.GetYourGuide.domain;

public class GuidedTour implements TypeOfOrder {
    @Override
    public String getDescription() {
        return "Guided tour";
    }

    @Override
    public double getCost() {
        return 200;
    }
}

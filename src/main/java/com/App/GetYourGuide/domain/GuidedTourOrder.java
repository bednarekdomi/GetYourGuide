package com.App.GetYourGuide.domain;

public class GuidedTourOrder extends BasicOrderDecorator {
    @Override
    public String getDescription() {
        return "Guided tour";
    }

    @Override
    public double getCost() {
        return 200;
    }
}

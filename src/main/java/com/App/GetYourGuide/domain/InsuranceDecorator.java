package com.App.GetYourGuide.domain;

public class InsuranceDecorator implements TypeOfOrder {
    private TypeOfOrder typeOfOrder;

    public InsuranceDecorator(TypeOfOrder typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }

    @Override
    public String getDescription() {
        return typeOfOrder.getDescription() + ", with mountaineering insurance";
    }

    @Override
    public double getCost() {
        return typeOfOrder.getCost() + 100;
    }
}

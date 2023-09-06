package com.App.GetYourGuide.domain;

public class EquipmentDecorator implements TypeOfOrder {
    private final TypeOfOrder typeOfOrder;

    public EquipmentDecorator(TypeOfOrder typeOfOrder) {
        this.typeOfOrder = typeOfOrder;
    }

    @Override
    public String getDescription() {
        return typeOfOrder.getDescription() + ", with equipment rental";
    }

    @Override
    public double getCost() {
        return typeOfOrder.getCost() + 50;
    }
}

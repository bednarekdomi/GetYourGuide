package com.App.GetYourGuide.domain;

public class EquipmentDecorator implements Order{
    private final Order order;

    public EquipmentDecorator(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        return order.getDescription() + ", with equipment rental";
    }

    @Override
    public double getCost() {
        return order.getCost() + 50;
    }
}

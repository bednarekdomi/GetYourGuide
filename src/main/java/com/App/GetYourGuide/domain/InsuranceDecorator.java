package com.App.GetYourGuide.domain;

public class InsuranceDecorator implements Order{
    private Order order;

    public InsuranceDecorator(Order order) {
        this.order = order;
    }

    @Override
    public String getDescription() {
        return order.getDescription() + ", with mountaineering insurance";
    }

    @Override
    public double getCost() {
        return order.getCost() + 100;
    }
}

package com.twu;

public class HotSearchItem {
    private int order;
    private String description;
    private int hotDegree;

    public HotSearchItem(int order, String description, int hotDegree) {
        this.order = order;
        this.description = description;
        this.hotDegree = hotDegree;
    }

    @Override
    public String toString() {
        return order + description + hotDegree;
    }
}

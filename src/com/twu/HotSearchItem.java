package com.twu;

public class HotSearchItem implements Comparable<HotSearchItem>{
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

    public int getHotDegree() {
        return this.hotDegree;
    }

    public int getOrder() {
        return this.order;
    }

    public String getDescription() {
        return this.description;
    }

    @Override
    public int compareTo(HotSearchItem o) {
        return o.getHotDegree() - hotDegree;
    }
}

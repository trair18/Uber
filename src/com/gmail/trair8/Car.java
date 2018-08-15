package com.gmail.trair8;

public class Car {
    private int currentX;
    private int currentY;
    private boolean isFree = true;
    private String name;

    public Car(String name) {
        this.name = name;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }


    @Override
    public String toString() {
        return "Car{" +
                "isFree=" + isFree +
                ", name='" + name + '\'' +
                '}';
    }
}

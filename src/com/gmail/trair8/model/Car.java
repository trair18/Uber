package com.gmail.trair8.model;

import java.util.concurrent.TimeUnit;

public class Car {
    private String name;
    private int currentX;
    private int currentY;


    public Car(String name, int currentX, int currentY) {
        this.name = name;
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public String getName() {
        return name;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void move (int x, int y) throws InterruptedException{
        double distance = Math.sqrt(Math.pow((x - this.getCurrentX()), 2)
                + Math.pow((y - this.getCurrentY()), 2));
        TimeUnit.SECONDS.sleep((int)distance);
        currentX = x;
        currentY = y;
    }

    @Override
    public String toString() {
        return "Car{" +
                "name='" + name + '\'' +
                ", currentX=" + currentX +
                ", currentY=" + currentY +
                '}';
    }
}

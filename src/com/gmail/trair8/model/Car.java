package com.gmail.trair8.model;

import java.util.concurrent.TimeUnit;

public class Car {
    private int id;
    private String name;
    private int currentX;
    private int currentY;

    public Car(int id, String name, int currentX, int currentY) {
        this.id = id;
        this.name = name;
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public int getId() {
        return id;
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
        double distance = Math.hypot(x - this.getCurrentX(),y - this.getCurrentY());
        TimeUnit.SECONDS.sleep((int)distance/4);
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

package com.gmail.trair8;

public class Car{
    private int currentX;
    private int currentY;
    private boolean isFree;


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
}

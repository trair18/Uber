package com.gmail.trair8;

public class Passenger{

    private int currentX;
    private int currentY;
    private Car car;

    public Passenger(int currentX, int currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void sendRequest(TaxiService taxiService){
        car = taxiService.processRequest(this.currentX, this.currentY);
        System.out.println(car);

    }

    @Override
    public String toString() {
        return "Passenger{" +
                "car=" + car +
                '}';
    }
}

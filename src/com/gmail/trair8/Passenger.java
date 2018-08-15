package com.gmail.trair8;

public class Passenger{
    private int currentX;
    private int currentY;
    private Car car;


    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public void sendRequest(TaxiService taxiService){
        taxiService.processRequest(this.currentX, this.currentY);
    }


}

package com.gmail.trair8.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Passenger implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger(Passenger.class);

    private String login;
    private int currentX;
    private int currentY;
    private int futureX;
    private int futureY;
    private Car car;
    private TaxiPark taxiPark;

    public Passenger(String login, int currentX, int currentY, int futureX, int futureY, TaxiPark taxiPark) {
        this.login = login;
        this.currentX = currentX;
        this.currentY = currentY;
        this.futureX = futureX;
        this.futureY = futureY;
        this.taxiPark = taxiPark;
    }

    public String getLogin() {
        return login;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "login='" + login + '\'' +
                ", currentX=" + currentX +
                ", currentY=" + currentY +
                ", car=" + car +
                '}';
    }

    @Override
    public void run() {
        try {
            this.setCar(taxiPark.getCar(this).get());
            System.out.println(car);
            LOGGER.debug(this);
            System.out.println(login + " car " + car.getId() + " rides");
            car.move(currentX, currentY);
            System.out.println(login + " car " + car.getId() + " arrived");
            car.move(futureX, futureY);
            System.out.println(login + " car " + car.getId() + " finished");
            taxiPark.returnCar(getCar());
        }catch (TaxiParkException e){
            LOGGER.error(e);
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            LOGGER.error(e);
        }
    }
}

package com.gmail.trair8.model;

import com.gmail.trair8.service.TaxiService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.ExecutionException;

public class Passenger implements Runnable {

    private final static Logger LOGGER = LogManager.getLogger(Passenger.class);

    private String login;
    private int currentX;
    private int currentY;
    private int futureX;
    private int futureY;
    private Car car;
    private TaxiService taxiService;

    public Passenger(String login, int currentX, int currentY, int futureX, int futureY, TaxiService taxiService) {
        this.login = login;
        this.currentX = currentX;
        this.currentY = currentY;
        this.futureX = futureX;
        this.futureY = futureY;
        this.taxiService = taxiService;
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
            this.setCar(taxiService.getCar(this).get());
            LOGGER.debug(this);
            System.out.println("car " + car.getName() + " rides");
            car.move(currentX, currentY);
            System.out.println("car " + car.getName() + " arrived");
            car.move(futureX, futureY);
            System.out.println("car " + car.getName() + " finished");
            taxiService.returnCar(getCar());
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
            LOGGER.error(e);
        }catch (ExecutionException e){

        }
    }
}

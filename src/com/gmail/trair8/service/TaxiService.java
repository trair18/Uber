package com.gmail.trair8.service;

import com.gmail.trair8.model.Car;
import com.gmail.trair8.model.Passenger;
import com.gmail.trair8.model.TaxiPark;

import java.util.ArrayDeque;
import java.util.Optional;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiService {

    private static Lock lock = new ReentrantLock();
    private static Condition isFree = lock.newCondition();

    private final static int QUANTITY = 3;
    private final Semaphore semaphore = new Semaphore(QUANTITY, true);

    public Optional<Car> getCar(Passenger passenger) throws ExecutionException, InterruptedException {
        semaphore.acquire();
        try {
            lock.lock();
            Car nearCar = null;
            double distance = 150;
            for (Car car : TaxiPark.getInstance().getCars()) {
                double currentDistance = Math.sqrt(Math.pow((car.getCurrentX() - passenger.getCurrentX()), 2)
                        + Math.pow((car.getCurrentY() - passenger.getCurrentY()), 2));
                if (currentDistance < distance) {
                    distance = currentDistance;
                    nearCar = car;
                }
            }
            TaxiPark.getInstance().getCars().remove(nearCar);
            return Optional.ofNullable(nearCar);
        } finally {
            lock.unlock();
        }
    }

    public void returnCar (Car car){
        TaxiPark.getInstance().getCars().add(car);
        semaphore.release();
    }
}

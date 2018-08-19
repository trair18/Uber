package com.gmail.trair8.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiPark {

    private static AtomicReference<TaxiPark> instance;

    private static List<Car> cars = new ArrayList<>();

    private static Lock lock = new ReentrantLock();

    private final static int QUANTITY = 5;
    private final Semaphore semaphore = new Semaphore(QUANTITY, true);

    private TaxiPark() {}

    public static List<Car> getCars() {
        return cars;
    }

    public static void setCars(List<Car> cars) {
        TaxiPark.cars = cars;
    }

    public static TaxiPark getInstance() {
        try {
            lock.lock();
            if (instance == null) {
                instance = new AtomicReference<>(new TaxiPark());
            }
        } finally {
            lock.unlock();
        }
        return instance.get();
    }

    public Optional<Car> getCar(Passenger passenger) throws TaxiParkException {
        try {
            semaphore.acquire();
            lock.lock();
            Car nearCar = null;
            double distance = 150;
            for (Car car : cars) {
                System.out.println(car);
                double currentDistance = Math.hypot(car.getCurrentX() - passenger.getCurrentX(),
                                                    car.getCurrentY() - passenger.getCurrentY());
                if (currentDistance < distance) {
                    distance = currentDistance;
                    nearCar = car;
                }
            }
            cars.remove(nearCar);
            return Optional.ofNullable(nearCar);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TaxiParkException(e);
        } finally {
            lock.unlock();
        }
    }

    public void returnCar (Car car){
        cars.add(car);
        semaphore.release();
    }

}
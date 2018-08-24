package com.gmail.trair8.model;

import com.gmail.trair8.exception.RepositoryException;
import com.gmail.trair8.exception.TaxiParkException;
import com.gmail.trair8.repository.TaxiParkRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiPark {

    private final static Logger LOGGER = LogManager.getLogger(TaxiPark.class);
    private static TaxiPark instance;
    private List<Car> cars;

    private static Lock lock = new ReentrantLock();
    private final Semaphore semaphore;

    public TaxiPark(List<Car> cars) {
        this.cars = cars;
        semaphore = new Semaphore(cars.size(), true);
    }

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    public static TaxiPark getInstance() {
        if (instance == null) {
            try {
                lock.lock();
                if (instance == null) {
                    try {
                        instance = new TaxiPark(new TaxiParkRepository().findAll());
                    } catch (RepositoryException e) {
                        LOGGER.error(e);
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return instance;
    }

    public Optional<Car> requestCar(Passenger passenger) throws TaxiParkException {
        try {
            semaphore.acquire();
            lock.lock();
            Car nearCar = null;
            double distance = 0;
            for (Car car : cars) {
                double currentDistance = Math.hypot(car.getCurrentX() - passenger.getCurrentX(),
                        car.getCurrentY() - passenger.getCurrentY());

                if (nearCar == null) {
                    nearCar = car;
                    distance = currentDistance;
                } else if (currentDistance < distance) {
                    distance = currentDistance;
                    nearCar = car;
                }
            }
            cars.remove(nearCar);
            return Optional.ofNullable(nearCar);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new TaxiParkException("Problem when trying to request car!", e);
        } finally {
            lock.unlock();
        }
    }

    public void releaseCar(Car car) {
        cars.add(car);
        semaphore.release();
    }


}
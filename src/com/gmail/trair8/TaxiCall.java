package com.gmail.trair8;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiCall implements Callable<Car> {

    private static Lock lock = new ReentrantLock();
    private static Condition isFree = lock.newCondition();
    private ArrayList<Car> cars = new ArrayList<Car>();

    @Override
    public Car call() throws Exception {



        try {
            lock.lock();
            for (Car car: cars) {
                if (car.isFree()){
                    car.setFree(false);
                    return car;
                }
            }
        }finally {
            lock.unlock();
        }
        return null;
    }
}

package com.gmail.trair8;

import java.util.ArrayList;
import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TaxiService implements Callable<Car> {

    private static Lock lock = new ReentrantLock();
    private static Condition isFree = lock.newCondition();

    public Car processRequest(int x, int y){
        ExecutorService es = Executors.newFixedThreadPool(5);
        Future<Car> future = es.submit(new TaxiService());
        es.shutdown();
        try {
            return future.get();
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            Thread.currentThread().interrupt();
        }
        return null;
    }

    @Override
    public Car call() throws Exception {
        try {
            lock.lock();
            for (Car car : TaxiPark.getInstance().getCars()) {
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

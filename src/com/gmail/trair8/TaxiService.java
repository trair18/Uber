package com.gmail.trair8;

import java.util.ArrayList;
import java.util.concurrent.*;

public class TaxiService {




    public Car processRequest(int x, int y){
        ExecutorService es = Executors.newFixedThreadPool(5);

        Future<Car> future = es.submit(new TaxiCall());
        try {
            return future.get();
        }catch (ExecutionException e){
            e.printStackTrace();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return null;

    }



}

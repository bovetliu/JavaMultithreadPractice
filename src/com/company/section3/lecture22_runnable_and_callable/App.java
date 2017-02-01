package com.company.section3.lecture22_runnable_and_callable;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by boweiliu on 1/30/17.
 */

class Processor implements Callable<String> {

    private int id;

    public Processor(int id ) {
        this.id = id;
    }

    @Override
    public String call() throws Exception {
        Thread.sleep(1000);
        return "Id: " + id;
    }
}

public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(2);

        List<Future<String>> strFutures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Future<String> strFuture = executorService.submit(new Processor(i + 1));
            strFutures.add(strFuture);
        }

        List<String> results = new ArrayList<>();

        for (Future<String> stringFuture : strFutures) {
            try {
                results.add(stringFuture.get());
                System.out.println(results.get(results.size() - 1));
            } catch (InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        executorService.shutdown();
    }
}

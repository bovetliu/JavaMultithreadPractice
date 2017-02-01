package com.company.section4.lecture23_count_down_latch;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by boweiliu on 1/30/17.
 */
public class App {

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        CountDownLatch latch = new CountDownLatch(5);

        for (int i = 0; i < 5; i++) {
            executorService.execute(new Worker(i + 1, latch));
        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("All the prerequisites are done...");
        executorService.shutdown();
    }
}

class Worker implements Runnable {

    private int id;
    private CountDownLatch countDownLatch;
    private Random random = new Random();

    public Worker (int id, CountDownLatch countDownLatch) {
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        doWork();
        countDownLatch.countDown();
    }

    private void doWork() {
        System.out.println("Thread with id " + this.id + " starts working...");
        try {
            Thread.sleep( (int) (random.nextDouble() * 5000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
package com.company.section3.lecture21_executor_service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 1 ) ExecutorService
 *
 */
public class App {
    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 10; i++) {
            executorService.submit(new Worker());
        }
    }
}


class Worker implements  Runnable {

    @Override
    public void run() {
        for (int i = 0; i < 10 ;i++) {
            System.out.println(Thread.currentThread().getName() + ":" + i);

            try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
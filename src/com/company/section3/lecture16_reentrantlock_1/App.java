package com.company.section3.lecture16_reentrantlock_1;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * lecture about reentrantLock
 * fair means the thread hadd been waiting for longest time obtain the lock when it is available
 * Created by boweiliu on 1/30/17.
 */
public class App {

    private static int counter = 0;
    private static Lock lock = new ReentrantLock();


    public static void increment() {
        lock.lock();
        try {
            for (int i = 0; i < 100000000; i++) {
                counter++;
            }
        } finally {
            lock.unlock();
        }
    }
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                increment();
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println(counter);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

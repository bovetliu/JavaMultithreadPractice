package com.company.section3.lecture17_reentrantlock2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by boweiliu on 1/30/17.
 */

class Worker {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public void producer() throws InterruptedException {
        lock.lock();
        System.out.println("Producer method...");
        condition.await();
        System.out.println("Producer again...");
        lock.unlock();
    }

    public void consumer() throws InterruptedException {
        lock.lock();
        Thread.sleep(3000);
        System.out.println("Consumer method...");
        condition.signal();
        lock.unlock();
    }
}
public class App {

    public static void main(String[] args) {

        Worker worker = new Worker();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.producer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    worker.consumer();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

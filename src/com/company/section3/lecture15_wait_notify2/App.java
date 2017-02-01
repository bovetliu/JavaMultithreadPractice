package com.company.section3.lecture15_wait_notify2;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by boweiliu on 1/30/17.
 */

class Processor {

    private List<Integer> list = new LinkedList();
    private final int LIMIT = 5;
    private final int BOTTOM = 0;
    private final Object lock = new Object();
    private int value = 0;
    // add items to list
    public void produce() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == LIMIT) {
                    System.out.println("Waiting for removing items from the list....");
                    lock.wait();
                } else {
                    System.out.println("Adding: " + value);
                    list.add(value);
                    value++;
                    lock.notify();
                }
                Thread.sleep(100);
            }
        }
    }

    // remove item from list
    public void consume() throws InterruptedException {
        synchronized (lock) {
            while (true) {
                if (list.size() == BOTTOM) {
                    System.out.println("Waiting for adding item to the list....");
                    lock.wait();
                } else {
                    System.out.println("Removed: " + list.remove(list.size() - 1));
                    lock.notify();
                }
                Thread.sleep(100);
            }
        }
    }
}
public class App {
    private static Processor processor = new Processor();

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.produce();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    processor.consume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        t1.start();
        t2.start();
    }
}

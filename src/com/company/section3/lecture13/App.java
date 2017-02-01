package com.company.section3.lecture13;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * Created by boweiliu on 1/30/17.
 */
public class App {

    private static int counter1 = 0;
    private static int counter2 = 0;

    private static final Object lock1 = new Object();
    private static final Object lock2 = new Object();


    /**
     * avoid using class intrinsic lock, when one method obtained the lock, is another method is being invoked, that
     * method invoking will be blocked until first method finishes execution, which is unnecessary.
     */
    public static void add() {
        synchronized (lock1) {
            counter1++;
        }
    }

    public static void addAgain() {
        synchronized (lock2) {
            counter2++;
        }
    }

    public static void compute() {
        for (int i = 0; i < 100000; i++) {
            add();
            addAgain();
        }
    }

    public static void main(String[] args) {
        Thread t1 = new Thread(new Runnable() {

            @Override
            public void run() {
                compute();
            }
        });

        Thread t2 = new Thread(new Runnable() {

            @Override
            public void run() {
                compute();
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

        System.out.println("Count1=" + counter1 + "  Count2=" + counter2);
    }

}

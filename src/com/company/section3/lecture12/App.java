package com.company.section3.lecture12;

/**
 * Created by boweiliu on 1/30/17.
 */
public class App {
    private static final Object object = new Object();

    private static long counter = 0;

    public static void process() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
//                    System.out.println("t1");
                    synchronized (object) {
                        ++counter;
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000000; i++) {
//                    System.out.println("t2");
                    synchronized (object) {
                        ++counter;
                    }
                }
            }
        });

        t2.start();
        t1.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        process();
        System.out.println(counter);
    }
}

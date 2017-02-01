package com.company.section3.lecture14_wait_notify_1;

/**
 * Created by boweiliu on 1/30/17.
 */

class Processor {
    public void produce() throws InterruptedException {
        synchronized (this) {
            System.out.println("We are in the producer method...");
            this.wait();  // let other thread get intrinsic lock
            System.out.println("Again producer method...");
        }
    }

    public void consume() throws InterruptedException {
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Consumer method...");
            this.notify();  // finished using intrinsic lock
        }
    }
}
public class App {

    public static void main(String[] args) {
        Processor processor = new Processor();

        Thread t1  = new Thread(() -> {
            try {
                processor.produce();

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        Thread t2  = new Thread(() -> {
            try {
                processor.consume();

            } catch (InterruptedException e) {
                e.printStackTrace();
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

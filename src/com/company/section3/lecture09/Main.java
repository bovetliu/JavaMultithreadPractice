package com.company.section3.lecture09;


class Runner1 extends  Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Runner 1 : " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException interrupedEx) {
                interrupedEx.printStackTrace();
            }
        }

    }
}

class Runner2 extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Runner 2 : " + i);
            try {
                Thread.sleep(10);
            } catch (InterruptedException interrupedEx) {
                interrupedEx.printStackTrace();
            }
        }

    }
}


public class Main {

    public static void main(String[] args) {
        // write your code here
        Runner1 t1 = new Runner1();
        Runner2 t2 = new Runner2();

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            System.out.println("have waited completion of t1 and t2 using join.");
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }
}

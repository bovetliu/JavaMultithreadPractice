package com.company.section3.lecture08;


class Runner1 extends  Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("Runner 1 : " + i);
            try {
                Thread.sleep(100);
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
                Thread.sleep(100);
            } catch (InterruptedException interrupedEx) {
                interrupedEx.printStackTrace();
            }
        }

    }
}


public class Main {

    public static void main(String[] args) {
	// write your code here
        Runner1 runner1 = new Runner1();
        Runner2 runner2 = new Runner2();

        runner1.start();
        runner2.start();
    }
}

package com.company.section5_dining_philosopher_ii;

import java.util.Random;

/**
 * Created by boweiliu on 2/15/17.
 */
public class Philosopher implements Runnable {

    private int id;

    private Chopstick leftChopstick;

    private Chopstick rightChopstick;

    private Random random;

    private int eatingTimeCounter;

    private volatile boolean isFull = false;

    public Philosopher(int id, Chopstick leftChopstick, Chopstick rightChopstick) {
        this.id = id;
        this.leftChopstick = leftChopstick;
        this.rightChopstick = rightChopstick;
        this.random = new Random();
    }

    @Override
    public void run() {
        try {
            while (!isFull) {
                think();
                if (leftChopstick.isPickedUpBy(this, State.LEFT)) {
                    if (rightChopstick.isPickedUpBy(this, State.RIGHT)) {
                        eat();
                        rightChopstick.isPutDownBy(this, State.RIGHT);

                    }
                    leftChopstick.isPutDownBy(this, State.LEFT);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void think() throws InterruptedException {
        System.out.println(this + " is thinking...");
        Thread.sleep(random.nextInt(1000));
    }

    private void eat() throws InterruptedException {
        System.out.println(this + " is eating...");
        int eatTime = random.nextInt(1000);
        eatingTimeCounter += eatTime;
        Thread.sleep(eatTime);
    }

    public int getEatingTimeCounter() {
        return eatingTimeCounter;
    }

    public void setFull(boolean isFull) {
        this.isFull = isFull;
    }

    @Override
    public String toString() {
        return " Philosopher " + id;
    }
}

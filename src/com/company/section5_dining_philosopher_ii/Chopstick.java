package com.company.section5_dining_philosopher_ii;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by boweiliu on 2/15/17.
 */
public class Chopstick {

    private int id;

    private Lock lock;

    public Chopstick(int id) {
        this.id = id;
        lock = new ReentrantLock();
    }

    public boolean isPickedUpBy( Philosopher philosopher, State state) throws InterruptedException {
        if (lock.tryLock(10, TimeUnit.MILLISECONDS)) {
            System.out.println(philosopher + " picked up " + state.toString() + " " + this);
            return true;
        }
        return false;
    }


    public void isPutDownBy(Philosopher philosopher, State state) {
        System.out.println(philosopher + " put down " + this);
        lock.unlock();
    }

    @Override
    public String toString() {
        return "chopstick " + id;
    }
}

package com.company.section6_student_library_problem;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Book {
    private int id;

    private Lock lock;

    private Random random = new Random();
    public Book(int id) {
        this.id = id;
        this.lock = new ReentrantLock();
    }

    public void read(Student student) throws InterruptedException {
        boolean obtained = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (obtained) {
            Thread.sleep(random.nextInt(2000) + 1000);  // occupying the resource for 2 seconds
            lock.unlock();// better be in finally
            System.out.println(student + " has finished reading book" + toString());

        } else
            System.out.println(student + " did not obtain access to " + this);
    }



    @Override
    public String toString() {
        return " Book #" + id;
    }

}

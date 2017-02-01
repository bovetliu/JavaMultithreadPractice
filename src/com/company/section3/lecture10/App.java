package com.company.section3.lecture10;

/**
 * Created by boweiliu on 1/30/17.
 */
class Worker implements Runnable {

    private volatile boolean isTerminated = false;

    @Override
    public void run() {

        while (!isTerminated) {
            System.out.println("Hello from worker instance.. ");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public boolean isTerminated() {
        return isTerminated;
    }

    public void setTerminated(boolean terminated) {
        isTerminated = terminated;
    }
}

public class App {

    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread workerThread = new Thread(worker);
        workerThread.start();
        try {
            Thread.sleep(10000);
        } catch ( InterruptedException ex) {
            ex.printStackTrace();
        }
        worker.setTerminated(true);
    }
}

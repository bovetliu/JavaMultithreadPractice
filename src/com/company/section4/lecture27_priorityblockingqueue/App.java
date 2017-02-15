package com.company.section4.lecture27_priorityblockingqueue;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by boweiliu on 2/12/17.
 */
class FirstWorker implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public FirstWorker(BlockingQueue<String> blockingQueueParam) {
        blockingQueue = blockingQueueParam;
    }

    @Override
    public void run() {
        try {
            blockingQueue.put("B");
            blockingQueue.put("H");
            blockingQueue.put("F");
            Thread.sleep(1000);
            blockingQueue.put("A");
            Thread.sleep(1000);
            blockingQueue.put("E");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}


class SecondWorker implements Runnable {

    private BlockingQueue<String> blockingQueue;

    public SecondWorker(BlockingQueue<String> blockingQueueParam) {
        blockingQueue = blockingQueueParam;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            Thread.sleep(1000);
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());
            System.out.println(blockingQueue.take());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
public class App {
    public static void main(String[] args) {
        BlockingQueue<String> queue = new PriorityBlockingQueue<>();

        new Thread(new FirstWorker(queue)).start();

        new Thread(new SecondWorker(queue)).start();
    }

}

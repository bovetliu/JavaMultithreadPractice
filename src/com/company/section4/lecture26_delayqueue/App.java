package com.company.section4.lecture26_delayqueue;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * Delayed queue. This one might be possible in deduplication and merging
 * Created by boweiliu on 2/12/17.
 */
public class App {

    public static void main(String[] args) {
        BlockingQueue<OperationRequest> queue = new DelayQueue<>();
        try {
            queue.put(new OperationRequest(1000, "This is the first message"));
            queue.put(new OperationRequest(10000, "This is the second message"));

            queue.put(new OperationRequest(4000, "This is the third message"));

        } catch (InterruptedException e ) {
            e.printStackTrace();
        }

        while (!queue.isEmpty()) {
            try {
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class Test {
    private final int id;
    public Test() {
        id = 3;
    }
}

class OperationRequest implements Delayed {

    private long duration;

    private String message;

    public OperationRequest(long durationParam, String messageParam) {
        duration = durationParam + System.currentTimeMillis();
        message = messageParam;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(duration - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        Objects.requireNonNull(o, "cannot compare to null");
        return Long.compare(duration, ((OperationRequest) o).getDuration() );
    }

    public long getDuration() {
        return duration;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return message;
    }
}

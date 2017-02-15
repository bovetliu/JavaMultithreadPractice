package com.company.section5_dining_philosopher_ii;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by boweiliu on 2/15/17.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = null;
        Philosopher[] philosophers = null;

        try {
            philosophers = new Philosopher[Constants.NUMBER_OF_PHILOSOPHER];
            Chopstick[] chopsticks = new Chopstick[Constants.NUMBER_OF_CHOPSTICK];

            for (int i = 0; i < chopsticks.length; i++) {
                chopsticks[i] = new Chopstick(i);
            }

            executorService = Executors.newFixedThreadPool(Constants.NUMBER_OF_PHILOSOPHER);

            for (int i = 0; i < Constants.NUMBER_OF_PHILOSOPHER; i++) {
                philosophers[i] = new Philosopher(i, chopsticks[i], chopsticks[(i + 1) % Constants.NUMBER_OF_CHOPSTICK]);
                executorService.execute(philosophers[i]);
            }

            Thread.sleep(Constants.SIMULATION_RUNNING_TIME);

            for (Philosopher p : philosophers) {
                p.setFull(true);
            }
        } finally {
            if (executorService != null) {
                executorService.shutdown();
                while(!executorService.isTerminated())
                    Thread.sleep(1000);
            }
            for (Philosopher p : philosophers) {
                System.out.println(p + " eats " + p.getEatingTimeCounter());
            }
        }
    }
}

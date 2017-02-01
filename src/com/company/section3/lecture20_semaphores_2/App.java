package com.company.section3.lecture20_semaphores_2;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * acquire()  if a permit is available then takes it
 * release()  adds a permit
 * Created by boweiliu on 1/30/17.
 */

enum Downloader {
    INSTANCE;

    private Semaphore semaphore = new Semaphore(3, true);

    public void downloadData() {
        try {
            semaphore.acquire();
            download();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    private void download() {
        System.out.println("Downloading data from the web....");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

public class App {
    public static void main(String[] args) throws InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();

        for (int i = 0; i < 12; i++) {
            Thread.sleep(300);
            executorService.execute(Downloader.INSTANCE::downloadData);
        }
    }
}

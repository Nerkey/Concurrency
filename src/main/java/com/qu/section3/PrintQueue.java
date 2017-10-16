package com.qu.section3;

import java.util.concurrent.Semaphore;

public class PrintQueue {

    private final Semaphore semaphore;

    public PrintQueue() {
        semaphore = new Semaphore(1, true);
    }

    public void printJob(Object document) {
        try {
            semaphore.acquire();

            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job during %d seconds\n", Thread.currentThread().getName(), duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
        }
    }

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i ++) {
            threads[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

}

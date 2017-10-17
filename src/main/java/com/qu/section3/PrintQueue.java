package com.qu.section3;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintQueue {

    private boolean[] freePrinters;

    private Lock lockPrinters;

    private final Semaphore semaphore;

    public PrintQueue() {
        semaphore = new Semaphore(3);
        freePrinters = new boolean[3];

        for (int i = 0; i < 3; i++) {
            freePrinters[i] = true;
        }

        lockPrinters = new ReentrantLock();
    }

    public void printJob(Object document) {
        try {
            semaphore.acquire(3);

            System.out.printf("%s: Going to print a job\n", Thread.currentThread().getName());

            int assignedPrinter = getPrinter();

            long duration = (long) (Math.random() * 10);
            System.out.printf("%s: PrintQueue: Printing a Job int Printer %d during %d seconds\n",
                    Thread.currentThread().getName(), assignedPrinter, duration);

            freePrinters[assignedPrinter] = true;
            TimeUnit.SECONDS.sleep(duration);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            semaphore.release();
            System.out.printf("%s: The documnet has been printed\n", Thread.currentThread().getName());
        }
    }

    private int getPrinter() {
        int ret = -1;

        try {
            lockPrinters.lock();
            for (int i = 0; i < freePrinters.length; i++) {
                if (freePrinters[i]) {
                    ret = i;
                    freePrinters[i] = false;
                    break;
                }
            }
        } finally {
            lockPrinters.unlock();
        }

        return ret;
    }

    public static void main(String[] args) {
        PrintQueue printQueue = new PrintQueue();

        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(new Job(printQueue), "Thread" + i);
        }

        for (int i = 0; i < 10; i++) {
            threads[i].start();
        }
    }

}

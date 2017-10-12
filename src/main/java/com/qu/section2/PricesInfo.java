package com.qu.section2;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo {

    private double price1;
    private double price2;


    private ReadWriteLock lock;

    public PricesInfo() {
        price1 = 1.0;
        price2 = 2.0;
        lock = new ReentrantReadWriteLock();
    }

    public double getPrice1() {
        lock.readLock().lock();
        double value = price1;
        System.out.printf("%s: Price 1: %f\n", Thread.currentThread().getName(), value);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "getPrice 1 end................");

        lock.readLock().unlock();
        return value;
    }


    public double getPrice2() {
        lock.readLock().lock();
        double value = price2;
        System.out.printf("%s: Price 2: %f\n", Thread.currentThread().getName(), value);

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "getPrice 2 end................");

        lock.readLock().unlock();
        return value;
    }

    public void setPrices(double price1, double price2) {
        lock.writeLock().lock();
        System.out.printf(Thread.currentThread() + " Writer: Attempt to modify the prices. \n");
        this.price1 = price1;
        this.price2 = price2;
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.printf(Thread.currentThread() + " Writer: Prices have been modified. \n");
        lock.writeLock().unlock();
    }

    public static void main(String[] args) {
        PricesInfo pricesInfo = new PricesInfo();

        Reader[] readers = new Reader[5];
        Thread[] threadsReader = new Thread[5];
        for (int i = 0; i < 5; i++) {
            readers[i] = new Reader(pricesInfo);
            threadsReader[i] = new Thread(readers[i]);
        }

        Writer[] writers = new Writer[5];
        Thread[] threadsWriter = new Thread[5];
        for (int i = 0; i < 5; i++) {
            writers[i] = new Writer(pricesInfo);
            threadsWriter[i] = new Thread(writers[i]);
        }

        for (int i = 0; i < 5; i++) {
            threadsReader[i].start();
        }
        for (int i = 0; i < 5; i++) {
            threadsWriter[i].start();
        }

    }



}

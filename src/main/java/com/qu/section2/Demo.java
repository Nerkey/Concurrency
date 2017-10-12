package com.qu.section2;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Demo {

    private Lock lock = new ReentrantLock();
    private Lock lock2 = new ReentrantLock();

    public void hello() {
        lock.lock();

        System.out.println("hello1");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("hello2");

        lock.unlock();
    }

    public void welcome() {
        lock2.lock();

        System.out.println("welcome1");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("welcome2");

        lock2.unlock();
    }

    public static void main(String[] args) {
        Demo demo = new Demo();
        Thread[] threads = new Thread[10];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Test1(demo));
        }

        for (int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}


class Test1 implements Runnable {
    private Demo demo;

    public Test1(Demo demo) {
        this.demo = demo;
    }

    @Override
    public void run() {
        demo.hello();
        demo.welcome();
    }
}



package com.qu.section2;

/**
 * synchronized 测试
 */
public class Test {
    public void method1() {
        synchronized (this)      //或者使用Test.this当参数
        {
            for (int i = 0; i < 10; i++) {
                System.out.println("块");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void method2() {
        for (int i = 0; i < 10; i++) {
            System.out.println("method");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        Test test = new Test();

        new Thread(test::method2).start();
        new Thread(test::method1).start();
    }
}
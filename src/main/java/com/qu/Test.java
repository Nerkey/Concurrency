package com.qu;

import java.util.Random;

public class Test {

    public static void main(String[] args) {
        Random r1 = new Random(10);

        Random r2 = new Random(10);

        for(int i = 0;i < 6;i++){

            System.out.println(r1.nextInt(5));
        }
        System.out.println("++++++++++++++++++++++");
        for(int i = 0;i < 6;i++){

            System.out.println(r2.nextInt(5));
        }
    }
}

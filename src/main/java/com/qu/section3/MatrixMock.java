package com.qu.section3;

import java.util.Random;
import java.util.concurrent.CyclicBarrier;

public class MatrixMock {

    private int data[][];

    public MatrixMock(int size, int length, int number) {
        int counter = 0;
        data = new int[size][length];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < length; j++) {
                data[i][j] = random.nextInt(10);
                if (data[i][j] == number) {
                    counter++;
                }
            }
        }

        System.out.printf("Mock: There are %d ocurrences of %d in generated data. \n", counter, number);
    }

    public int[] getRow(int row) {
        if ((row >= 0) && (row < data.length)) {
            return data[row];
        }
        return null;
    }

    public static void main(String[] args) {
        final int ROWS = 10000; // 行
        final int NUMBERS = 1000;// 列
        final int SEARCH = 5; // 搜索的数字
        final int PARTICIPANTS = 5; // 线程数
        final int LINES_PARTICIPANT = 2000;

        MatrixMock mock = new MatrixMock(ROWS, NUMBERS, SEARCH);

        Results results = new Results(ROWS);

        Grouper grouper = new Grouper(results);

        CyclicBarrier barrier = new CyclicBarrier(PARTICIPANTS, grouper); // 这个grouper线程是到达同步状态时调用

        Searcher searcher[] = new Searcher[PARTICIPANTS];
        for (int i = 0; i < PARTICIPANTS; i++) {
            searcher[i] = new Searcher(i * LINES_PARTICIPANT, (i * LINES_PARTICIPANT) + LINES_PARTICIPANT, mock, results, 5, barrier);
            Thread thread = new Thread(searcher[i]);
            thread.start();
        }
        System.out.printf("Main: The main thread has finished. \n");
    }

}

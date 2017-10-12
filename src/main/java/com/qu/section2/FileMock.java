package com.qu.section2;

public class FileMock {

    private String content[];
    private int index;

    /**
     * 创建一个容量size, 每一个元素长度为length的字符串数组
     * @param size 数组的个数
     * @param length 每个元素的长度
     */
    public FileMock(int size, int length) {
        content = new String[size];
        for (int i = 0; i < size; i++) {
            StringBuilder buffer = new StringBuilder(length);
            for (int j = 0; j < length; j++) {
                int indice = (int) (Math.random() * 255);
                buffer.append((char) indice);
            }
            content[i] = buffer.toString();
        }
        index = 0;
    }

    public boolean hasMoreLines() {
        return index < content.length;
    }

    public String getLine() {
        System.out.println("Mock: " + (content.length - index));
        return content[index++];
    }

    public static void main(String[] args) {
        FileMock mock = new FileMock(100, 10);
        Buffer buffer = new Buffer(20);

        Producer1 producer = new Producer1(mock, buffer);
        Thread threadProducer = new Thread(producer, "Producer");

        Consumer1 consumers[] = new Consumer1[3];
        Thread threadConsumers[] = new Thread[3];
        for (int i = 0; i < 3; i++) {
            consumers[i] = new Consumer1(buffer);
            threadConsumers[i] = new Thread(consumers[i], "Consumer " + i);
        }

        threadProducer.start();
        for (int i = 0; i < 3; i++) {
            threadConsumers[i].start();
        }
    }

}

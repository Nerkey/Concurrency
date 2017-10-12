package com.qu.section2;

public class Producer1 implements Runnable {

    private FileMock mock;
    private Buffer buffer;

    public Producer1(FileMock mock, Buffer buffer) {
        this.mock = mock;
        this.buffer = buffer;
    }

    @Override
    public void run() {
        buffer.setPendingLines(true); // 设置buffer中有行
        while (mock.hasMoreLines()) { // 文件中是否有行
            String line = mock.getLine(); // 取出行
            buffer.insert(line); // 插入到buffer中
        }
        buffer.setPendingLines(false);
    }
}

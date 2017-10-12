package com.qu.section2;

public class Reader implements Runnable {

    private PricesInfo pricesInfo;

    public Reader(PricesInfo pricesInfo) {
        this.pricesInfo = pricesInfo;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            pricesInfo.getPrice1();
            pricesInfo.getPrice2();



        }
    }
}

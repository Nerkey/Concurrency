package com.qu.section1;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class NetworkConnectionsLoader implements Runnable {

    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(6);
        } catch (InterruptedException e) {
            System.out.printf("Connections Interrupted");
        }

        System.out.printf("Beginning network connections loading: %s \n", new Date());
    }

}

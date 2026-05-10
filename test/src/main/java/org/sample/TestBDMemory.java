package org.sample;

import java.math.BigDecimal;

public class TestBDMemory {


    public static void main(String[] args) throws InterruptedException {
        int size = 100_000_000;

        BigDecimal[] bdVals = new BigDecimal[size];
        for(int i = 0; i < size; i++) {
            bdVals[i] = BigDecimal.valueOf(Math.random());
        }

        Thread.currentThread().sleep(30_000);
        System.out.println(bdVals.length);
    }
}

package org.sample;

public class TestDoublesMemory {


    public static void main(String[] args) throws InterruptedException {
        int size = 100_000_000;
        double[] vals = new double[size];
        for(int i = 0; i < size; i++) {
            vals[i] = Math.random();
        }


        Thread.currentThread().sleep(30_000);
        System.out.println(vals.length);
    }
}

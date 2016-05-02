package com.chaturv.misc;

public class VolatileTest {

    private volatile int MY_INT = 0;

    public static void main(String[] args) {
        new VolatileTest().start();
    }

    public void start() {
        new ChangeListener().start();
        new ChangeMaker().start();
    }

    class ChangeListener extends Thread {

        @Override
        public void run() {
            int local_value = MY_INT;
            while ( local_value < 5) {
                if( local_value != MY_INT){
                    System.out.println("Got Change for MY_INT : " + MY_INT);
                    local_value= MY_INT;
                }
            }
        }
    }

    class ChangeMaker extends Thread{
        @Override
        public void run() {

            int local_value = MY_INT;
            while (MY_INT < 5) {
                System.out.println("Incrementing MY_INT to " + (local_value + 1));
                MY_INT = ++local_value;
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

package com.chaturv.collections;

public class TicksConsumer implements Runnable {

    private TicksService service;

    public TicksConsumer(TicksService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            System.out.println("calling service...");
            System.out.println("got from service: " + service.getLatest());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

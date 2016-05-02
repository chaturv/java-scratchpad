package com.chaturv.collections;

import java.math.BigDecimal;

public class TicksProducer implements Runnable {

    private TicksService service;

    public TicksProducer(TicksService service) {
        this.service = service;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            service.acceptTick(new BigDecimal(99));
        }
    }
}
